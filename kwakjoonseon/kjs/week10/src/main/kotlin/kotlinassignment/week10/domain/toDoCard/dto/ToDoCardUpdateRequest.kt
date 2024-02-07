package kotlinassignment.week10.domain.toDoCard.dto

import jakarta.validation.constraints.NotBlank

data class ToDoCardUpdateRequest(
    @field: NotBlank(message = "할 일 제목은 필수값입니다.") val title: String,
    @field: NotBlank(message = "할 일 본문은 필수값입니다.") val description: String,
)
