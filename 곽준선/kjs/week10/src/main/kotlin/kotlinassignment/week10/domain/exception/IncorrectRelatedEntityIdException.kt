package kotlinassignment.week10.domain.exception

data class IncorrectRelatedEntityIdException(
    val modelName1: String,
    val id1: Long,
    val modelName2: String,
    val id2: Long,
    ) : RuntimeException(
    "$modelName1(id = $id1)과 $modelName2(id = $id2)는 서로 관련 없는 모델입니다."
)
