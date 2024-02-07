package kotlinassignment.week10.domain.toDoCard.dto

import kotlinassignment.week10.domain.toDoCard.model.ToDoCard
import java.time.LocalDateTime

data class ToDoCardResponse(
    val id: Long,
    val title: String,
    val description: String?,
    val memberNickname: String,
    val createdDateTime: LocalDateTime,
    val isComplete: Boolean,
)

fun ToDoCard.toResponse(): ToDoCardResponse {
    return ToDoCardResponse(
        id = this.id!!,
        title = this.title,
        description = this.description,
        memberNickname = this.member.nickname,
        createdDateTime = this.createdDateTime,
        isComplete = this.isComplete,
    )
}
