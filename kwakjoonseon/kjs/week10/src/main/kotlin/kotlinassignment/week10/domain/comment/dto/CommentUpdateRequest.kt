package kotlinassignment.week10.domain.comment.dto

import kotlinassignment.week10.domain.comment.model.Comment

data class CommentUpdateRequest(
    val content: String,
)

fun Comment.updateFrom(request: CommentUpdateRequest): Comment {
    this.content = request.content
    return this
}
