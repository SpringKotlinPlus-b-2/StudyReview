package kotlinassignment.week10.domain.toDoCard.repository

import kotlinassignment.week10.domain.toDoCard.model.ToDoCard
import org.springframework.data.jpa.repository.JpaRepository

interface ToDoCardRepository : JpaRepository<ToDoCard, Long>, CustomToDoCardRepository {
    fun findByIdAndDeletedAtIsNull(toDoCardId: Long): ToDoCard?
}
