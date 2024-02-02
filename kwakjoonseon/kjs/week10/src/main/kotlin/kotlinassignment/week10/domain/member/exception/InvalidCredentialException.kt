package kotlinassignment.week10.domain.member.exception

class InvalidCredentialException(
    override val message: String? = "자격 증명에 실패했습니다."
): RuntimeException()
