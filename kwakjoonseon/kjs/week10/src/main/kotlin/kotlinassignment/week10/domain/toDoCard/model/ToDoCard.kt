package kotlinassignment.week10.domain.toDoCard.model

import jakarta.persistence.*
import kotlinassignment.week10.domain.comment.model.Comment
import kotlinassignment.week10.domain.exception.StringLengthOutOfRangeException // TODO 엔티티가 커스텀한 exception을 알고 있는 게 괜찮을지 고민해보기
import kotlinassignment.week10.domain.member.model.Member
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

    companion object {
        const val TITLE_MIN_LENGTH: Long = 1
        const val TITLE_MAX_LENGTH: Long = 200
        const val DESCRIPTION_MIN_LENGTH: Long = 1
        const val DESCRIPTION_MAX_LENGTH: Long = 1_000
    }
}
