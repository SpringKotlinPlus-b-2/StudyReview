package kotlinassignment.week10.domain.toDoCard.repository

import com.querydsl.core.types.dsl.BooleanExpression
import kotlinassignment.week10.domain.toDoCard.model.QToDoCard
import kotlinassignment.week10.domain.toDoCard.model.ToDoCard
import kotlinassignment.week10.infra.querydsl.QueryDslSupport

// @Repository // 이것마저 붙이지 않아도 동작함... https://docs.spring.io/spring-data/jpa/reference/repositories/custom-implementations.html
class CustomToDoCardRepositoryImpl : CustomToDoCardRepository, QueryDslSupport() {
    // QueryDslSupport를 상속하므로 entityManager와 queryFactory를 참조할 수 있음

    private val toDoCard = QToDoCard.toDoCard

    override fun findAllFilteringByTitleOrUserNameWithSortOrder(
        title: String?,
        memberNickname: String?,
        sortOrder: String,
    ): List<ToDoCard> {
        return queryFactory.selectFrom(toDoCard)
            .where(titleContains(title))
            .where(memberNicknameEq(memberNickname))
            .orderBy(
                when (sortOrder) {
                    "ASC" -> toDoCard.createdDateTime.asc()
                    "DESC" -> toDoCard.createdDateTime.desc()
                    else -> toDoCard.createdDateTime.desc()
                }
            ).fetch()
    }

    private fun titleContains(title: String?): BooleanExpression? {
        return if (title != null) toDoCard.title.contains(title) else null
    }

    private fun memberNicknameEq(memberNickname: String?): BooleanExpression? {
        return if (memberNickname != null) toDoCard.member.nickname.eq(memberNickname) else null
    }
}
