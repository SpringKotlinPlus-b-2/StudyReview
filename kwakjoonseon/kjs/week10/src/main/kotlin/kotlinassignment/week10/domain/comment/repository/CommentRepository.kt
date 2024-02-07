package kotlinassignment.week10.domain.comment.repository;

import kotlinassignment.week10.domain.comment.model.Comment
import kotlinassignment.week10.domain.toDoCard.model.ToDoCard
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {

    fun findByIdAndToDoCard_Id(commentId: Long, toDoCardId: Long): Comment?

    fun findByToDoCard_IdOrderByIdDesc(toDoCardId: Long, pageable: Pageable): Page<Comment>
}
