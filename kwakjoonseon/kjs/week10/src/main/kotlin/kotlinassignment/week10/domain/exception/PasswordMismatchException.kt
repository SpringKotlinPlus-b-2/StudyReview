package kotlinassignment.week10.domain.exception

data class PasswordMismatchException(val modelName: String, val id: Long) : RuntimeException(
    "$modelName(id = $id)의 상태 변경을 위해 입력된 password가 일치하지 않습니다."
)
