package kotlinassignment.week10.domain.exception

import java.io.Serial

data class PasswordMismatchException(val modelName: String, val id: Long) : RuntimeException(
    "$modelName(id = $id)의 상태 변경을 위해 입력된 password가 일치하지 않습니다."
) {

    companion object {
        @Serial
        private const val serialVersionUID: Long = 7884190870189302136L
    }
}
