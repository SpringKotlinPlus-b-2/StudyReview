package kotlinassignment.week10.domain.toDoCard.controller

import jakarta.validation.Valid
import kotlinassignment.week10.domain.toDoCard.dto.ToDoCardCreateRequest
import kotlinassignment.week10.domain.toDoCard.dto.ToDoCardIsCompletePatchRequest
import kotlinassignment.week10.domain.toDoCard.dto.ToDoCardResponse
import kotlinassignment.week10.domain.toDoCard.dto.ToDoCardUpdateRequest
import kotlinassignment.week10.domain.toDoCard.service.ToDoCardService
import kotlinassignment.week10.infra.security.MemberPrincipal
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RequestMapping("/todocards")
@RestController
@Validated
class ToDoCardController(
    private val toDoCardService: ToDoCardService,
) {
    // cf. url "/todocards/에 대한 GET: getToDoCard(), PUT: updateToDoCard(), DELETE: deleteToDoCard()는
    // path variable 없는 /todocards/{toDoCardId} 요청을 일괄적으로 handling 하기 위해 추가되었다.
    // required = false 방법은 작동하지 않았음

    @GetMapping
    fun getToDoCardList(
        @RequestParam(required = false) title: String?,
        @RequestParam(required = false) memberNickname: String?,
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, name = "sort", defaultValue = "DESC") sortOrder: Sort.Direction,
    ): ResponseEntity<Page<ToDoCardResponse>> {
        val pageable: Pageable = PageRequest.of(page, TO_DO_CARD_PAGE_SIZE, sortOrder, TO_DO_CARD_SORT_PROPERTY)

        val toDoCardResponsesPage = toDoCardService.getToDoCardList(title, memberNickname, pageable)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(toDoCardResponsesPage)
    }

    @GetMapping("/{toDoCardId}")
    fun getToDoCard(@PathVariable toDoCardId: Long): ResponseEntity<ToDoCardResponse> {
        val toDoCardResponse = toDoCardService.getToDoCardById(toDoCardId)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(toDoCardResponse)
    }

    @PostMapping
    fun createToDoCard(
        @Valid @RequestBody request: ToDoCardCreateRequest,
        @AuthenticationPrincipal memberPrincipal: MemberPrincipal,
    ): ResponseEntity<ToDoCardResponse> {
        val toDoCardResponse = toDoCardService.createToDoCard(request, memberPrincipal)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(toDoCardResponse)
    }

    @PutMapping("/{toDoCardId}")
    fun updateToDoCard(
        @PathVariable toDoCardId: Long,
        @Valid @RequestBody request: ToDoCardUpdateRequest,
        @AuthenticationPrincipal memberPrincipal: MemberPrincipal,
    ): ResponseEntity<ToDoCardResponse> {
        val toDoCardResponse = toDoCardService.updateToDoCard(toDoCardId, request, memberPrincipal)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(toDoCardResponse)
    }

    @PatchMapping("/{toDoCardId}")
    fun completeToDoCard(
        @PathVariable toDoCardId: Long,
        @Valid @RequestBody request: ToDoCardIsCompletePatchRequest,
        @AuthenticationPrincipal memberPrincipal: MemberPrincipal,
    ): ResponseEntity<ToDoCardResponse> {
        val toDoCardResponse = toDoCardService.completeToDoCard(toDoCardId, request, memberPrincipal)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(toDoCardResponse)
    }

    @DeleteMapping("/{toDoCardId}")
    fun deleteToDoCard(
        @PathVariable toDoCardId: Long,
        @AuthenticationPrincipal memberPrincipal: MemberPrincipal,
    ): ResponseEntity<Unit> {
        toDoCardService.deleteToDoCard(toDoCardId, memberPrincipal)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

    companion object {
        private const val TO_DO_CARD_PAGE_SIZE = 5
        private const val TO_DO_CARD_SORT_PROPERTY = "createdDateTime"
    }
}
