package kotlinassignment.week10.domain.member.model

import jakarta.persistence.*

@Entity
@Table(name = "member")
class Member(
    val email: String,
    var password: String,
    var nickname: String,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
