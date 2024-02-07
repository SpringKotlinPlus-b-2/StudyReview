package kotlinassignment.week10.domain.exception

import java.io.Serial

data class UnauthorizedAccessException(
    override val message: String = "해당 자원의 소유자, 관리자가 아닌 경우 요청할 수 없습니다."
) : RuntimeException() {

    companion object {
        @Serial
        private const val serialVersionUID: Long = -3812553086481651217L
    }
}
