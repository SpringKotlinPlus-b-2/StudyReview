package kotlinassignment.week10.domain.comment.model

import jakarta.persistence.*
import kotlinassignment.week10.domain.member.model.Member
import kotlinassignment.week10.domain.toDoCard.model.ToDoCard
import kotlinassignment.week10.domain.util.BaseEntity
import java.time.LocalDateTime

@Entity
class Comment(
    @Column(nullable = false) var content: String,

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "member_id") val member: Member,

    // FetchType.Lazy 지정하지 않을 경우 CommentService의 수정, 삭제 메서드에서 불필요하게 ToDoCard select 쿼리가 한 번 더 나감
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "to_do_card_id", nullable = false) val toDoCard: ToDoCard,
) : BaseEntity() {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
}
