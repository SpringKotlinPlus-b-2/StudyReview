package kotlinassignment.week10.domain.toDoCard.model

import jakarta.persistence.*
import kotlinassignment.week10.domain.comment.dto.CommentResponse
import kotlinassignment.week10.domain.comment.model.Comment
import kotlinassignment.week10.domain.comment.model.toResponse
import kotlinassignment.week10.domain.exception.StringLengthOutOfRangeException
import kotlinassignment.week10.domain.member.model.Member
import kotlinassignment.week10.domain.toDoCard.dto.ToDoCardResponse
import kotlinassignment.week10.domain.toDoCard.dto.ToDoCardResponseWithComments
import java.time.LocalDateTime

@Entity
class ToDoCard(
    title: String,
    description: String,

    @ManyToOne(fetch = FetchType.LAZY)
    val member: Member,

    @Column(nullable = false)
    val createdDateTime: LocalDateTime,

    @OneToMany(mappedBy = "toDoCard", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val comments: MutableList<Comment> = mutableListOf(),
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false)
    var title = getStringAfterLengthValidation(title, "title", minLength = TITLE_MIN_LENGTH, maxLength = TITLE_MAX_LENGTH)
        set(value) {
            field = getStringAfterLengthValidation(value, "title", minLength = TITLE_MIN_LENGTH, maxLength = TITLE_MAX_LENGTH)
        }

    @Column(nullable = false)
    var description = getStringAfterLengthValidation(description, "description", minLength = DESCRIPTION_MIN_LENGTH, maxLength = DESCRIPTION_MAX_LENGTH)
        set(value) {
            field = getStringAfterLengthValidation(value, "description", minLength = DESCRIPTION_MIN_LENGTH, maxLength = DESCRIPTION_MAX_LENGTH)
        }

    @Column(columnDefinition = "boolean default false") // https://m.blog.naver.com/younjh5369/222763814571 // https://www.baeldung.com/jpa-default-column-values
    var isComplete: Boolean = false

    private fun getStringAfterLengthValidation(target: String, propertyName: String, minLength: Long, maxLength: Long): String {
        if (target.length in minLength..maxLength)
            return target
        else throw StringLengthOutOfRangeException(propertyName, minLength = minLength, maxLength = maxLength)
    }
}

const val TITLE_MIN_LENGTH: Long = 1
const val TITLE_MAX_LENGTH: Long = 200
const val DESCRIPTION_MIN_LENGTH: Long = 1
const val DESCRIPTION_MAX_LENGTH: Long = 1_000

fun ToDoCard.toResponse(): ToDoCardResponse {
    return ToDoCardResponse(
        id = this.id!!,
        title = this.title,
        description = this.description,
        memberNickname = this.member.nickname,
        createdDateTime = this.createdDateTime,
        isComplete = this.isComplete,
    )
}

fun ToDoCard.toResponseWithComments(): ToDoCardResponseWithComments {
    /*
     ?? ToDoCard의 comments를 사용하지 않고, comment repository에서 페이징 처리한 comment를 가져오며 사용하지 않게 된 메서드
     ToDoCard가 참조하고 있는 comments를 사용하지 않고, comment repository 쪽에서 따로 Page 처리된 Comment들을 가져옴
     ToDoCard에서 직접 comments를 꺼내올 때도 paging 처리되게 하려면 어떻게 해야하는지 알아보기
     */
    return ToDoCardResponseWithComments(
        id = this.id!!,
        title = this.title,
        description = this.description,
        memberNickname = this.member.nickname,
        createdDateTime = this.createdDateTime,
        comments = this.comments.map { it.toResponse() }.sortedByDescending { it.createdDateTime },
        isComplete = this.isComplete,
    )
}

fun ToDoCard.toResponseWithComments(comments: List<CommentResponse>): ToDoCardResponseWithComments {
    return ToDoCardResponseWithComments(
        id = this.id!!,
        title = this.title,
        description = this.description,
        memberNickname = this.member.nickname,
        createdDateTime = this.createdDateTime,
        comments = comments,
        isComplete = this.isComplete,
    )
}

/*
// CommentService에서 createComment() 할 때 더티 체킹 사용하지 않게 되면서 주석 처리
fun ToDoCard.addComment(comment: Comment) {
    this.comments.add(comment)
}
 */
