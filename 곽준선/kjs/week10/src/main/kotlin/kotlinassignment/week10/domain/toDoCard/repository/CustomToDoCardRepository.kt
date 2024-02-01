package kotlinassignment.week10.domain.toDoCard.repository

import kotlinassignment.week10.domain.toDoCard.model.ToDoCard

interface CustomToDoCardRepository {

    fun findAllFilterByUserNameAndOrderBySortOrder(
        userName: String?,
        sortOrder: String,
    ): List<ToDoCard>
}
