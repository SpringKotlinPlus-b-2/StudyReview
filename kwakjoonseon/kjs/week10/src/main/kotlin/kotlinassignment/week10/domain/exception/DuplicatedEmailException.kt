package kotlinassignment.week10.domain.exception

data class DuplicatedEmailException(
    override val message: String = "중복된 email이 존재합니다."
) : RuntimeException()
