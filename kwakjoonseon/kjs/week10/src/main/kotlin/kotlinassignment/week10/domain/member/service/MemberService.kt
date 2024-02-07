package kotlinassignment.week10.domain.member.service

import kotlinassignment.week10.domain.member.dto.MemberLoginRequest
import kotlinassignment.week10.domain.member.dto.MemberLoginResponse
import kotlinassignment.week10.domain.member.dto.MemberSignUpRequest

interface MemberService {

    /**
     * @param (type: MemberLoginRequest) 회원 로그인을 위한 DTO
     * @return (type: MemberLoginResponse) 로그인 후 반환하는 데이터
     */
    fun login(request: MemberLoginRequest): MemberLoginResponse

    /**
     * @param (type: MemberSignUpRequest) 회원 가입을 위한 DTO
     * @return 없음
     */
    fun signUp(request: MemberSignUpRequest): Unit
}
