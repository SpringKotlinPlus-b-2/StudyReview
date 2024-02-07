package kotlinassignment.week10.domain.util

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
abstract class BaseEntity {

    @Column(updatable = false) val createdAt: LocalDateTime = LocalDateTime.now()

    @Column @LastModifiedDate lateinit var lastModifiedAt: LocalDateTime // lateinit var로 두면 알아서 입력 // 메인 클래스에 @EnableJpaAuditing 빼먹으면 안 됨

    @Column var deletedAt: LocalDateTime? = null
}
