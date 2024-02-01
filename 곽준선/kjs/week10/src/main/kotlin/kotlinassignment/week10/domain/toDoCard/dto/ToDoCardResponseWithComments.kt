package kotlinassignment.week10.domain.toDoCard.dto

import kotlinassignment.week10.domain.comment.dto.CommentResponse
import java.time.LocalDateTime

data class ToDoCardResponseWithComments(
    val id: Long,
    val title: String,
    val description: String?,
    val memberNickname: String, // TODO nickname 등으로 수정 필요 - entity까지 수정 필요해짐
    val createdDateTime: LocalDateTime,
    val isComplete: Boolean,
    // val comments: List<Comment>, // entity 그대로 사용하면 순환 참조 https://dev-coco.tistory.com/133
    val comments: List<CommentResponse>,
)
