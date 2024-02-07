package xyz.moveuk.post.infra.security.filter

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class EmailPasswordAuthenticationFilter(authenticationManager: AuthenticationManager?) :
    UsernamePasswordAuthenticationFilter(authenticationManager) {
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {

//        try {
//            val creds = ObjectMapper().readValue(request?.inputStream, LoginRequest::class.java)
//
//            val userId = it.payload.subject.toLong()
//            val role = it.payload.get("role", String::class.java)
//            val email = it.payload.get("email", String::class.java)
//
//            val principal = UserPrincipal(
//                id = userId,
//                email = email,
//                roles = setOf(role)
//            )
//            val authentication = JwtAuthenticationToken(
//                principal = principal,
//                details =  WebAuthenticationDetailsSource().buildDetails(request)
//            )
//            SecurityContextHolder.getContext().authentication = authentication
//        } catch (e: IOException){
//            throw RestApiException(CommonErrorCode.JWT_VERIFICATION_FAILED)
//        }

        return super.attemptAuthentication(request, response)
    }
}