package kotlinassignment.week10.domain.toDoCard.service

import kotlinassignment.week10.domain.toDoCard.dto.*
import kotlinassignment.week10.infra.security.MemberPrincipal
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ToDoCardService {

    /**
     * @param title (type: String?) ToDoCard 조회 시 title로 필터링할 경우 필요
     * @param category (type: String?) ToDoCard 조회 시 category로 필터링할 경우 필요
     * @param tag (type: String?) ToDoCard 조회 시 tag로 필터링할 경우 필요
     * @param state (type: String?) ToDoCard 조회 시 state 필터링할 경우 필요
     * @param dayDuration (type: String?) ToDoCard 조회 시 dayDuration로 필터링할 경우 필요
     * @param memberNickname (type: String?) ToDoCard 조회 시 member의 nickname으로 필터링할 경우 필요
     * @param pageable (type: Pageable) ToDoCard 목록을 가져올 때 필요한 page number, page size, sort 등의 정보를 담은 pageable
     * @return (type: Page<ToDoCardResponse>) ToDoCard 데이터 목록을 Page 형태로 담아 반환
     */
    fun getToDoCardList(
        title: String?,
        category: String?,
        tag: String?,
        state: String?,
        dayDuration: String?,
        memberNickname: String?,
        pageable: Pageable
    ): Page<ToDoCardResponse>

    /**
     * @param toDoCardId (type: Long) 조회할 ToDoCard의 id
     * @return (type: ToDoCardResponse) parameter로 받은 ToDoCard id와 일치하는 id를 가진 ToDoCard 조회 결과 반환
     */
    fun getToDoCardById(toDoCardId: Long): ToDoCardResponse

    /**
     * @param request (type: ToDoCardCreateRequest) ToDoCard 생성을 위한 DTO
     * @param memberPrincipal (type: MemberPrincipal) 인증된 member의 정보를 담고 있는 객체
     * @return (type: ToDoCardResponse) ToDoCard 생성 후 생성된 ToDoCard를 보여주기 위해 반환하는 데이터
     */
    fun createToDoCard(request: ToDoCardCreateRequest, memberPrincipal: MemberPrincipal): ToDoCardResponse

    /**
     * @param toDoCardId (type: Long) 수정할 ToDoCard의 id
     * @param request (type: ToDoCardUpdateRequest) ToDoCard 수정을 위한 DTO
     * @param memberPrincipal (type: MemberPrincipal) 인증된 member의 정보를 담고 있는 객체
     * @return (type: ToDoCardResponse) ToDoCard 수정 후 수정된 ToDoCard를 보여주기 위해 반환하는 데이터
     */
    fun updateToDoCard(toDoCardId: Long, request: ToDoCardUpdateRequest, memberPrincipal: MemberPrincipal): ToDoCardResponse

    /**
     * @param toDoCardId (type: Long) 삭제할 ToDoCard의 id
     * @param memberPrincipal (type: MemberPrincipal) 인증된 member의 정보를 담고 있는 객체
     * @return (type: Unit) 없음
     */
    fun deleteToDoCard(toDoCardId: Long, memberPrincipal: MemberPrincipal): Unit

    /**
     * @param toDoCardId (type: Long) 완료 처리할 ToDoCard의 id
     * @param request (type: ToDoCardIsCompletePatchRequest) ToDoCard 완료 여부 수정을 위한 DTO
     * @param memberPrincipal (type: MemberPrincipal) 인증된 member의 정보를 담고 있는 객체
     * @return (type: ToDoCardResponse) ToDoCard 완료 여부 수정 후 수정된 ToDoCard를 보여주기 위해 반환하는 데이터
     */
    fun completeToDoCard(toDoCardId: Long, request: ToDoCardIsCompletePatchRequest, memberPrincipal: MemberPrincipal): ToDoCardResponse
}
