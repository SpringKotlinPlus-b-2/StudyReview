package kotlinassignment.week10.domain.toDoCard.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull

/*
* https://stackoverflow.com/questions/52444699/kotlin-spring-bean-validation-nullability
* - @NotNull validation을 정말 써야겠다면 아래와 같은 방법을 사용해야될 듯하다.
* - 적어도 service로 노출되는 값에서는 nullable 변수를 없애준다.
* --------------------------------------------------------------------
* - 다른 방법으로는 controller DTO와 service DTO를 분리해서
*   controller DTO에서만 nullable 타입을 사용하고 service DTO에서 넘겨받을 때 nonnull 타입으로 받는 방법이 있다.
* --------------------------------------------------------------------
* - 하지만 가장 근본적으로는
*   이런 엣지 케이스가 정말 발생할 만한 일인지(FE가 잘못하지 않았다는 가정 하에)
*   리소스를 투입할만한 가치가 있는 예외 처리인지
*   고민이 필요
* */
data class ToDoCardIsCompletePatchRequest(
    // ?? 작동 방식 알아보기 - 생각보다 작동 방식이 어려운 듯함
    // - private이 빠지면 Jackson 매핑 에러가 발생하고, val isComplete = _isComplete!!으로 받아오려고 하면 validation이 안 됨
    @field: NotNull(message = "완료 여부는 필수값입니다.")
    @JsonProperty("isComplete")
    private val _isComplete: Boolean?,
) {
    val isComplete
        get() = _isComplete!!
}
