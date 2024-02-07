package kotlinassignment.week10.domain.comment.service

import kotlinassignment.week10.domain.comment.dto.CommentCreateRequest
import kotlinassignment.week10.domain.comment.dto.CommentResponse
import kotlinassignment.week10.domain.comment.dto.CommentUpdateRequest
import kotlinassignment.week10.infra.security.MemberPrincipal

interface CommentService {

    /**
     * @param toDoCardId (type: Long) Comment와 연관된 ToDoCard의 id
     * @param request (type: CommentCreateRequest) Comment 생성을 위한 DTO
     * @param memberPrincipal (type: MemberPrincipal) 인증된 member의 정보를 담고 있는 객체
     * @return (type: CommentResponse) Comment 생성 후 생성된 Comment를 보여주기 위해 반환하는 데이터
     */
    fun createComment(toDoCardId: Long, request: CommentCreateRequest, memberPrincipal: MemberPrincipal): CommentResponse

    /**
     * @param toDoCardId (type: Long) Comment와 연관된 ToDoCard의 id
     * @param commentId (type: Long) 수정할 Comment의 id
     * @param request (type: CommentUpdateRequest) Comment 수정을 위한 DTO
     * @param memberPrincipal (type: MemberPrincipal) 인증된 member의 정보를 담고 있는 객체
     * @return (type: CommentResponse) Comment 수정 후 수정된 Comment를 보여주기 위해 반환하는 데이터
     */
    fun updateComment(toDoCardId: Long, commentId: Long, request: CommentUpdateRequest, memberPrincipal: MemberPrincipal): CommentResponse

    /**
     * @param toDoCardId (type: Long) Comment와 연관된 ToDoCard의 id
     * @param commentId (type: Long) 삭제할 Comment의 id
     * @param memberPrincipal (type: MemberPrincipal) 인증된 member의 정보를 담고 있는 객체
     * @return 없음
     */
    fun deleteComment(toDoCardId: Long, commentId: Long, memberPrincipal: MemberPrincipal): Unit
}
