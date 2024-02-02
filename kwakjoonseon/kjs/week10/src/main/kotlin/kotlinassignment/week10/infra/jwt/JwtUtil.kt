package kotlinassignment.week10.infra.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.Instant
import java.util.*

@Component
class JwtUtil(
    @Value("\${authentication.member.jwt.issuer}") private val issuer: String,
    @Value("\${authentication.member.jwt.secret}") secret: String,
    @Value("\${authentication.member.jwt.accessTokenExpirationHour}") private val accessTokenExpirationHour: Long,
) {

    private val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))

    fun generateAccessToken(subject: String, email: String): String {
        return generateToken(subject, email, Duration.ofHours(accessTokenExpirationHour))
    }

    // refresh token 메서드 만들 때를 생각해서 메서드 분리
    private fun generateToken(subject: String, email: String, expirationPeriod: Duration): String {
        // ClaimBuilder 사용하여 custom claim 만들기
        val claims: Claims = Jwts.claims()
            .add(mapOf("email" to email))
            .build()
        val now = Instant.now()

        return Jwts.builder()
            .subject(subject)
            .issuer(issuer)
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plus(expirationPeriod)))
            .claims(claims)
            .signWith(key)
            .compact()
    }

    fun validateToken(jwt: String): Result<Jws<Claims>> {
        return kotlin.runCatching {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt) // key를 이용해 서명을 검증하고, 만료시간을 체크
        }
    }
}
