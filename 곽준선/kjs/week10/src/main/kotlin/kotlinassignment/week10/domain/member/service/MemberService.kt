package kotlinassignment.week10.domain.member.service

import kotlinassignment.week10.domain.member.dto.MemberLoginRequest
import kotlinassignment.week10.domain.member.dto.MemberLoginResponse
import kotlinassignment.week10.domain.member.dto.MemberSignUpRequest
import kotlinassignment.week10.domain.member.exception.InvalidCredentialException
import kotlinassignment.week10.domain.member.model.Member
import kotlinassignment.week10.domain.member.repository.MemberRepository
import kotlinassignment.week10.infra.jwt.JwtUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil,
) {

    fun login(request: MemberLoginRequest): MemberLoginResponse {
        val user = memberRepository.findByEmail(request.email) ?: throw InvalidCredentialException()

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw InvalidCredentialException()
        }

        return MemberLoginResponse(accessToken = jwtUtil.generateAccessToken(user.id.toString(), user.email))
    }

    @Transactional
    fun signUp(request: MemberSignUpRequest): Unit {
        if (memberRepository.existsByEmail(request.email)) {
            throw IllegalStateException("Email is already in use")
        }

        memberRepository.save(
            Member(
                email = request.email,
                password = passwordEncoder.encode(request.password),
                nickname = request.nickname,
            )
        )
    }
}
