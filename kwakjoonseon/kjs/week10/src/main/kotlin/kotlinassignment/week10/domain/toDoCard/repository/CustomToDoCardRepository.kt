package kotlinassignment.week10.domain.toDoCard.repository

import kotlinassignment.week10.domain.toDoCard.model.ToDoCard

interface CustomToDoCardRepository {

    fun findAllFilteringByTitleOrUserNameWithSortOrder(
        title: String?,
        memberNickname: String?,
        sortOrder: String,
    ): List<ToDoCard>
}
