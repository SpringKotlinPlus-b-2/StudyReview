package kotlinassignment.week10.domain.member.dto

data class MemberSignUpRequest(
    val email: String,
    val password: String,
    val nickname: String,
)
