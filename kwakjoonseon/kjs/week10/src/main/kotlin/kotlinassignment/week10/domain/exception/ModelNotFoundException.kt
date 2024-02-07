package kotlinassignment.week10.domain.exception

data class ModelNotFoundException(val modelName: String, val id: Long) : RuntimeException(
    "$modelName 중 다음 id 값을 가진 모델이 없습니다: $id"
)
