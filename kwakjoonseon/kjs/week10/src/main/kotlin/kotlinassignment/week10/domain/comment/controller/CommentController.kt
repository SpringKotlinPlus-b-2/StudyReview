package kotlinassignment.week10.domain.comment.controller

import kotlinassignment.week10.domain.comment.dto.CommentCreateRequest
import kotlinassignment.week10.domain.comment.dto.CommentResponse
import kotlinassignment.week10.domain.comment.dto.CommentUpdateRequest
import kotlinassignment.week10.domain.comment.service.CommentService
import kotlinassignment.week10.infra.security.MemberPrincipal
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/todocards/{toDoCardId}/comments/")
@RestController
class CommentController(
    private val commentService: CommentService
) {

    @GetMapping
    fun getCommentList(
        @PathVariable toDoCardId: Long,
        @RequestParam(required = false, defaultValue = "0") page: Int,
    ): ResponseEntity<Page<CommentResponse>> {
        val pageable = PageRequest.of(page, COMMENT_PAGE_SIZE)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.getCommentList(toDoCardId, pageable))
    }

    @PostMapping
    fun createComment(
        @PathVariable toDoCardId: Long,
        @RequestBody request: CommentCreateRequest,
        @AuthenticationPrincipal memberPrincipal: MemberPrincipal,
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(toDoCardId, request, memberPrincipal))
    }

    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable toDoCardId: Long,
        @PathVariable commentId: Long,
        @RequestBody request: CommentUpdateRequest,
        @AuthenticationPrincipal memberPrincipal: MemberPrincipal,
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(toDoCardId, commentId, request, memberPrincipal))
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable toDoCardId: Long,
        @PathVariable commentId: Long,
        @AuthenticationPrincipal memberPrincipal: MemberPrincipal,
    ): ResponseEntity<Unit> {
        commentService.deleteComment(toDoCardId, commentId, memberPrincipal)

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    companion object {
        private const val COMMENT_PAGE_SIZE = 50
    }
}
