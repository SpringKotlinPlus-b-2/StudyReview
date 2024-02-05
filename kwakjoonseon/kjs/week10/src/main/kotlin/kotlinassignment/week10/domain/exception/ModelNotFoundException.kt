package kotlinassignment.week10.domain.exception

import java.io.Serial

data class ModelNotFoundException(val modelName: String, val id: Long) : RuntimeException(
    "$modelName 중 다음 id 값을 가진 모델이 없거나 연관 관계를 가진 모델이 없습니다: $id"
) {

    companion object {
        @Serial
        private const val serialVersionUID: Long = 978551572999481421L
    }
}
