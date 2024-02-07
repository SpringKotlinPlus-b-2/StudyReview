package kotlinassignment.week10.infra.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kotlinassignment.week10.infra.jwt.JwtUtil
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(private val jwtUtil: JwtUtil) : OncePerRequestFilter() {

    companion object {
        private val BEARER_PATTERN = Regex("^Bearer (.+?)$")
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = request.getBearerToken()

        if (jwt != null) {
            jwtUtil.validateToken(jwt)
                .onSuccess {
                    val userId = it.payload.subject.toLong()
                    val email = it.payload.get("email", String::class.java)

                    // 검증 성공한 경우 Authentication 타입 객체 만들어서 SecurityContext에 저장
                    JwtAuthenticationToken(
                        MemberPrincipal(id = userId, email = email),
                        WebAuthenticationDetailsSource().buildDetails(request),
                    )
                        .also { authentication -> SecurityContextHolder.getContext().authentication = authentication }
                }
        }

        filterChain.doFilter(request, response)
    }

    private fun HttpServletRequest.getBearerToken(): String? {
        return this.getHeader(HttpHeaders.AUTHORIZATION)
            ?.let { BEARER_PATTERN.find(it)?.groupValues?.get(1) }
    }
}
