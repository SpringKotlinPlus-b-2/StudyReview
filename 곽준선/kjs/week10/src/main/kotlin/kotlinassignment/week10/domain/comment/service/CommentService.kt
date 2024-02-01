package kotlinassignment.week10.domain.comment.service

import kotlinassignment.week10.domain.comment.dto.CommentCreateRequest
import kotlinassignment.week10.domain.comment.dto.CommentResponse
import kotlinassignment.week10.domain.comment.dto.CommentUpdateRequest
import kotlinassignment.week10.domain.comment.model.Comment
import kotlinassignment.week10.domain.comment.model.toResponse
import kotlinassignment.week10.domain.comment.model.updateFrom
import kotlinassignment.week10.domain.comment.repository.CommentRepository
import kotlinassignment.week10.domain.exception.IncorrectRelatedEntityIdException
import kotlinassignment.week10.domain.exception.ModelNotFoundException
import kotlinassignment.week10.domain.exception.UnauthorizedAccessException
import kotlinassignment.week10.domain.member.repository.MemberRepository
import kotlinassignment.week10.domain.toDoCard.repository.ToDoCardRepository
import kotlinassignment.week10.infra.security.MemberPrincipal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    private val toDoCardIdRepository: ToDoCardRepository,
    private val commentRepository: CommentRepository,
    private val memberRepository: MemberRepository,
) {

    @Transactional
    fun createComment(toDoCardId: Long, request: CommentCreateRequest, memberPrincipal: MemberPrincipal): CommentResponse {
        val targetToDoCard = toDoCardIdRepository.findByIdOrNull(toDoCardId) ?: throw ModelNotFoundException("ToDoCard", toDoCardId)
        val member = memberRepository.findByIdOrNull(memberPrincipal.id) ?:throw ModelNotFoundException("Member", memberPrincipal.id)

        return Comment(
            content = request.content,
            member = member,
            createdDateTime = request.createdDateTime,
            toDoCard = targetToDoCard,
        ).let { commentRepository.save(it).toResponse() }
    }

    @Transactional
    fun updateComment(
        toDoCardId: Long,
        commentId: Long,
        request: CommentUpdateRequest,
        memberPrincipal: MemberPrincipal
    ): CommentResponse {
        val targetComment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)

        return targetComment
            .also { if (it.toDoCard.id != toDoCardId) throw IncorrectRelatedEntityIdException("Comment", commentId, "ToDoCard", toDoCardId) } // Comment의 toDoCard 참조 FetchType.Lazy 지정하지 않을 경우 불필요하게 ToDoCard select 쿼리가 한 번 더 나감
            .also { if (it.member.id != memberPrincipal.id) throw UnauthorizedAccessException() }
            .also { it.updateFrom(request) }
            .let { commentRepository.save(it).toResponse() }
    }

    @Transactional
    fun deleteComment(toDoCardId: Long, commentId: Long, memberPrincipal: MemberPrincipal): Unit {
        val targetComment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)

        targetComment
            .also { if (it.toDoCard.id != toDoCardId) throw IncorrectRelatedEntityIdException("Comment", commentId, "ToDoCard", toDoCardId) }
            .also { if (it.member.id != memberPrincipal.id) throw UnauthorizedAccessException() }
            .let { commentRepository.delete(it) }
    }

    /*
    // comment entity 정책 변경으로 필요 없어진 메서드
    private fun getCommentIfUserNameAndPasswordMatches(commentId: Long, userNameRequested: String, passwordRequested: String): Comment {
        // ?? propagation = PROPAGATION.REQUIRED로 될까? 이렇게 따로 추출한 메서드는 transaction 처리 어떻게 되는지 알아보기
        // 암호화 될 password는 서버까지 끌고 오지 않고, DB 서버 내에서 비교 후 일치하면 처리되도록 할 것
        return commentRepository.findByIdAndUserNameAndPassword(commentId, userNameRequested, passwordRequested)
            ?: throw PasswordMismatchException("Comment", commentId)
    }
     */
}
