package xyz.moveuk.post.domain.post.model

import jakarta.persistence.*


@Entity
@Table(name = "post_hit", indexes = [Index(columnList = "post_id")])
class PostHit(
    @Column(name = "post_id")
    val postId: Long
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null
}