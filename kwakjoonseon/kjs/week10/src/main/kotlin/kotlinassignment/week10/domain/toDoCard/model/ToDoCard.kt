package kotlinassignment.week10.domain.toDoCard.model

import jakarta.persistence.*
import kotlinassignment.week10.domain.comment.model.Comment
import kotlinassignment.week10.domain.exception.StringLengthOutOfRangeException // TODO 엔티티가 커스텀한 exception을 알고 있는 게 괜찮을지 고민해보기
import kotlinassignment.week10.domain.member.model.Member
import kotlinassignment.week10.domain.util.BaseEntity
import java.time.LocalDateTime

@Entity
class ToDoCard(
    title: String,

    description: String,

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "member_id") val member: Member,

    @OneToMany(mappedBy = "toDoCard", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val comments: MutableList<Comment> = mutableListOf(),
) : BaseEntity() {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null

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

    // https://m.blog.naver.com/younjh5369/222763814571 // https://www.baeldung.com/jpa-default-column-values
    @Column(columnDefinition = "boolean default false") var isComplete: Boolean = false

    @Column var category: String? = null

    @Column var tag: String? = null

    @Column var state: String? = null // TODO category, tag, state 관련 조금 더 정제할 것

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
