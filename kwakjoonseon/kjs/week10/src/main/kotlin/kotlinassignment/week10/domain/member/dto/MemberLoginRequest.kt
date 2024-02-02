package kotlinassignment.week10.domain.member.dto

data class MemberLoginRequest(
    val email: String,
    val password: String,
)
