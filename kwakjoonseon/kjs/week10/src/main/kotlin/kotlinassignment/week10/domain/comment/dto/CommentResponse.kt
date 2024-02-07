package kotlinassignment.week10.domain.comment.dto

import kotlinassignment.week10.domain.comment.model.Comment
import java.time.LocalDateTime

data class CommentResponse(
    val id: Long,
    val createdAt: LocalDateTime,
    val content: String,
    val memberNickname: String,
    val toDoCardId: Long,
)

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        id = this.id!!,
        createdAt = this.createdAt,
        content = this.content,
        memberNickname = this.member.nickname,
        toDoCardId = this.toDoCard.id!!,
    )
}
