package kotlinassignment.week10.domain.member.controller

import kotlinassignment.week10.domain.member.dto.MemberLoginRequest
import kotlinassignment.week10.domain.member.dto.MemberLoginResponse
import kotlinassignment.week10.domain.member.dto.MemberSignUpRequest
import kotlinassignment.week10.domain.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val memberService: MemberService
) {

    @PostMapping("/login")
    fun login(@RequestBody request: MemberLoginRequest): ResponseEntity<MemberLoginResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.login(request))
    }

    @PostMapping("/signup")
    fun signUp(@RequestBody request: MemberSignUpRequest): ResponseEntity<Unit> {
        // TODO 일단 최대한 단순한 형태로 구현함, response 내용도 따로 주지 않고, 성공 실패 여부만 응답
        return memberService.signUp(request)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }
    }
}
