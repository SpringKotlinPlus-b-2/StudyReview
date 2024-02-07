package kotlinassignment.week10.domain.toDoCard.repository

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.dsl.BooleanExpression
import kotlinassignment.week10.domain.toDoCard.model.QToDoCard
import kotlinassignment.week10.domain.toDoCard.model.ToDoCard
import kotlinassignment.week10.infra.querydsl.QueryDslSupport
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort.Direction.ASC
import org.springframework.data.domain.Sort.Direction.DESC
import java.time.LocalDate

// @Repository // 이것마저 붙이지 않아도 동작함... https://docs.spring.io/spring-data/jpa/reference/repositories/custom-implementations.html
class CustomToDoCardRepositoryImpl : CustomToDoCardRepository, QueryDslSupport() {
    // QueryDslSupport를 상속하므로 entityManager와 queryFactory를 참조할 수 있음

    private val toDoCard = QToDoCard.toDoCard

    override fun findAllFilteringByTitleOrUserName(
        title: String?,
        category: String?,
        tag: String?,
        state: String?,
        dayDuration: String?,
        memberNickname: String?,
        pageable: Pageable
    ): Page<ToDoCard> {

        val whereClause = allCondition(title, category, tag, state, dayDuration, memberNickname)

        val totalCount = queryFactory.select(toDoCard.count()).from(toDoCard).where(whereClause).fetchOne() ?: 0L

        val queryResult = queryFactory.selectFrom(toDoCard)
            .where(whereClause)
            .orderBy(
                when (pageable.sort.first()?.direction) {
                    DESC -> toDoCard.id.desc()
                    ASC -> toDoCard.id.asc()
                    null -> toDoCard.id.desc()
                }
            ).fetch()

        return PageImpl(queryResult, pageable, totalCount)
    }

    private fun allCondition(
        title: String?,
        category: String?,
        tag: String?,
        state: String?,
        dayDuration: String?,
        memberNickname: String?
    ): BooleanBuilder? {
        return BooleanBuilder()
            .and(titleContains(title))
            .and(categoryEq(category))
            .and(tagContains(tag))
            .and(stateEq(state))
            .and(withInDays(dayDuration))
            .and(memberNicknameEq(memberNickname))
            .and(deletedAtIsNull())
    }

    private fun titleContains(title: String?): BooleanExpression? {
        return if (title != null) toDoCard.title.contains(title) else null
    }

    private fun categoryEq(category: String?): BooleanExpression? {
        return if (category != null) toDoCard.category.eq(category) else null
    }

    private fun tagContains(tag: String?): BooleanExpression? {
        return if (tag != null) toDoCard.tag.contains(tag) else null
    }

    private fun stateEq(state: String?): BooleanExpression? {
        return if (state != null) toDoCard.state.eq(state) else null
    }

    private fun withInDays(dayDuration: String?): BooleanExpression? {
        return if (dayDuration != null) {
            toDoCard.createdAt.after(LocalDate.now().minusDays(dayDuration.toLong()).atTime(0, 0))
        } else null
    }

    private fun memberNicknameEq(memberNickname: String?): BooleanExpression? {
        return if (memberNickname != null) toDoCard.member.nickname.eq(memberNickname) else null
    }

    private fun deletedAtIsNull(): BooleanExpression {
        return toDoCard.deletedAt.isNull
    }
}
