package kotlinassignment.week10.domain.member.model

import jakarta.persistence.*
import kotlinassignment.week10.domain.util.BaseEntity

@Entity
class Member(
    val email: String,

    var password: String,

    var nickname: String,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
