package kotlinassignment.week10.domain.exception

import java.io.Serial

data class StringLengthOutOfRangeException(val dataName: String, val minLength: Long, val maxLength: Long) : RuntimeException(
    "${dataName}의 길이 허용 범위는 $minLength 이상, $maxLength 이하입니다."
) {

    companion object {
        @Serial
        private const val serialVersionUID: Long = 4319816374872340730L
    }
}
