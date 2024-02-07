package kotlinassignment.week10.domain.exception

import java.io.Serial

data class DuplicatedEmailException(
    override val message: String = "중복된 email이 존재합니다."
) : RuntimeException() {

    companion object {
        @Serial
        private const val serialVersionUID: Long = 7109371816251238467L
    }
}
