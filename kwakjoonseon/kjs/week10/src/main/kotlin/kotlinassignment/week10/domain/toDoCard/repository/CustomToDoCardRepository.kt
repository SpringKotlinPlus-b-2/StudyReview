package kotlinassignment.week10.domain.toDoCard.repository

import kotlinassignment.week10.domain.toDoCard.model.ToDoCard
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomToDoCardRepository {

    fun findAllFilteringByTitleOrUserName(
        title: String?,
        memberNickname: String?,
        pageable: Pageable,
    ): Page<ToDoCard>
}
