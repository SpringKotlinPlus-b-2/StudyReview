package kotlinassignment.week10.domain.toDoCard.dto

import kotlinassignment.week10.domain.toDoCard.model.ToDoCard
import java.time.LocalDateTime

data class ToDoCardResponse(
    val id: Long,
    val createdAt: LocalDateTime,
    val title: String,
    val description: String?,
    val memberNickname: String,
    val isComplete: Boolean,
)

fun ToDoCard.toResponse(): ToDoCardResponse {
    return ToDoCardResponse(
        id = this.id!!,
        createdAt = this.createdAt,
        title = this.title,
        description = this.description,
        memberNickname = this.member.nickname,
        isComplete = this.isComplete,
    )
}
