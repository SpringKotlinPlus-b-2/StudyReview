package kotlinassignment.week10.domain.toDoCard.dto

import java.time.LocalDateTime

data class ToDoCardResponse(
    val id: Long,
    val title: String,
    val description: String?,
    val memberNickname: String, // TODO nickname 등으로 수정 필요 - entity까지 수정 필요해짐
    val createdDateTime: LocalDateTime,
    val isComplete: Boolean,
)
