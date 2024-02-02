package kotlinassignment.week10.infra.security

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.web.authentication.WebAuthenticationDetails

class JwtAuthenticationToken(
    private val principal: MemberPrincipal,
    details: WebAuthenticationDetails, // 로깅을 위한 요청 정보 담는 곳
): AbstractAuthenticationToken(listOf()) { // 현재 역할, 권한 아무것도 구현하지 않아서 일단 authorities는 빈 컬렉션으로 둠

    init {
        super.setAuthenticated(true)
        super.setDetails(details)
    }

    override fun getCredentials() = null

    override fun getPrincipal() = principal

    override fun isAuthenticated() = true
}
