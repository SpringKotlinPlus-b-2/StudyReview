package kotlinassignment.week10.domain.comment.dto

import java.time.LocalDateTime

data class CommentCreateRequest(
    val content: String,
    val createdDateTime: LocalDateTime,
)
