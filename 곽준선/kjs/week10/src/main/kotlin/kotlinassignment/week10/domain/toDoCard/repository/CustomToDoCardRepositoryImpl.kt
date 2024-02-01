package kotlinassignment.week10.domain.toDoCard.repository

import kotlinassignment.week10.domain.toDoCard.model.QToDoCard
import kotlinassignment.week10.domain.toDoCard.model.ToDoCard
import kotlinassignment.week10.infra.querydsl.QueryDslSupport

// @Repository // 이것마저 붙이지 않아도 동작함... https://docs.spring.io/spring-data/jpa/reference/repositories/custom-implementations.html
class CustomToDoCardRepositoryImpl : CustomToDoCardRepository, QueryDslSupport() {
    // QueryDslSupport를 상속하므로 entityManager와 queryFactory를 참조할 수 있음

    private val toDoCard = QToDoCard.toDoCard

    override fun findAllFilterByUserNameAndOrderBySortOrder(
        userName: String?,
        sortOrder: String,
    ): List<ToDoCard> {
        return queryFactory.selectFrom(toDoCard)
            .let { if (userName != null) it.where(toDoCard.member.email.eq(userName)) else it } // TODO 컴파일 에러 방지용 임시 저장
            .orderBy(
                when (sortOrder) {
                    "ASC" -> toDoCard.createdDateTime.asc()
                    "DESC" -> toDoCard.createdDateTime.desc()
                    else -> toDoCard.createdDateTime.desc()
                }
            ).fetch()
    }
}