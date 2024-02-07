package kotlinassignment.week10.domain.comment.dto

import kotlinassignment.week10.domain.comment.model.Comment
import java.time.LocalDateTime

data class CommentResponse(
    val id: Long,
    val content: String,
    val memberNickname: String,
    val createdDateTime: LocalDateTime,
    val toDoCardId: Long,
)

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        id = this.id!!,
        content = this.content,
        memberNickname = this.member.nickname,
        createdDateTime = this.createdDateTime,
        toDoCardId = this.toDoCard.id!!,
    )
}
