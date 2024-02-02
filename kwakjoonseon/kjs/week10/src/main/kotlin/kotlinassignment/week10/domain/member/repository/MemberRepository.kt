package kotlinassignment.week10.domain.member.repository

import kotlinassignment.week10.domain.member.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {

    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): Member?
}
