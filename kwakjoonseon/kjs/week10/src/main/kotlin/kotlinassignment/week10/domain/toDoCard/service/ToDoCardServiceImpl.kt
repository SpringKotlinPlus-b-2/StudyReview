package kotlinassignment.week10.domain.toDoCard.service

import kotlinassignment.week10.domain.comment.model.toResponse
import kotlinassignment.week10.domain.comment.repository.CommentRepository
import kotlinassignment.week10.domain.exception.ModelNotFoundException
import kotlinassignment.week10.domain.exception.UnauthorizedAccessException
import kotlinassignment.week10.domain.member.repository.MemberRepository
import kotlinassignment.week10.domain.toDoCard.dto.*
import kotlinassignment.week10.domain.toDoCard.model.*
import kotlinassignment.week10.domain.toDoCard.repository.ToDoCardRepository
import kotlinassignment.week10.infra.aop.EvaluateExecutionTime
import kotlinassignment.week10.infra.security.MemberPrincipal
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ToDoCardServiceImpl(
    private val toDoCardRepository: ToDoCardRepository,
    private val commentRepository: CommentRepository,
    private val memberRepository: MemberRepository,
): ToDoCardService {

    override fun getAllToDoCards(userName: String?, sortOrder: String): List<ToDoCardResponse> {
        return toDoCardRepository
            .findAllFilterByUserNameAndOrderBySortOrder(userName, sortOrder)
            .map(ToDoCard::toResponse)
    }

    @EvaluateExecutionTime
    override fun getToDoCardById(toDoCardId: Long): ToDoCardResponseWithComments {
        val toDoCard = toDoCardRepository.findByIdOrNull(toDoCardId) ?: throw ModelNotFoundException("ToDoCard", toDoCardId)

        /*
         TODO ToDoCard가 참조하고 있는 comments를 사용하지 않고, comment repository 쪽에서 따로 Page 처리된 Comment들을 가져옴
          ToDoCard에서 직접 comments를 꺼내올 때도 paging 처리되게 하려면 어떻게 해야하는지 알아보기
         */
        val pageRequest = PageRequest.of(0, 100) // TODO 이 부분은 임시로 값을 넣어놨음, 추후 제대로 처리할 것
        val commentPage =
            commentRepository.findByToDoCardOrderByCreatedDateTimeDesc(toDoCard, pageRequest)

        return toDoCard.toResponseWithComments(commentPage.map { it.toResponse() }.toList())
    }

    @Transactional
    override fun createToDoCard(
        request: ToDoCardCreateRequest,
        memberPrincipal: MemberPrincipal,
    ): ToDoCardResponse {

        val member = memberRepository.findByIdOrNull(memberPrincipal.id) ?: throw ModelNotFoundException("Member", memberPrincipal.id)

        return ToDoCard(
            title = request.title,
            description = request.description,
            member = member,
            createdDateTime = request.createdDateTime,
        ).let { toDoCardRepository.save(it).toResponse() }
    }

    @Transactional
    override fun updateToDoCard(toDoCardId: Long, request: ToDoCardUpdateRequest, memberPrincipal: MemberPrincipal): ToDoCardResponse {
        val toDoCard = toDoCardRepository.findByIdOrNull(toDoCardId)
            ?.also { if (it.member.id != memberPrincipal.id) throw UnauthorizedAccessException() }
            ?: throw ModelNotFoundException("ToDoCard", toDoCardId)

        val (title: String?, description: String?) = request
        toDoCard.title = title
        toDoCard.description = description

        return toDoCardRepository.save(toDoCard).toResponse()
    }

    @Transactional
    override fun deleteToDoCard(toDoCardId: Long, memberPrincipal: MemberPrincipal): Unit {
        // 이 부분이 있어도 select query는 한 번만 나간다. 없는 id로 delete 시도하는 경우 확실한 에러 메시지를 주기 위함
        val toDoCard = toDoCardRepository.findByIdOrNull(toDoCardId)
            ?.also { if (it.member.id != memberPrincipal.id) throw UnauthorizedAccessException() }
            ?: throw ModelNotFoundException("ToDoCard", toDoCardId)

        return toDoCardRepository.delete(toDoCard)
    }

    @Transactional
    override fun completeToDoCard(toDoCardId: Long, request: ToDoCardIsCompletePatchRequest, memberPrincipal: MemberPrincipal): ToDoCardResponse {
        val toDoCard = toDoCardRepository.findByIdOrNull(toDoCardId)
            ?.also { if (it.member.id != memberPrincipal.id) throw UnauthorizedAccessException() }
            ?: throw ModelNotFoundException("ToDoCard", toDoCardId)
        toDoCard.isComplete = request.isComplete

        return toDoCard.toResponse()
    }
}
