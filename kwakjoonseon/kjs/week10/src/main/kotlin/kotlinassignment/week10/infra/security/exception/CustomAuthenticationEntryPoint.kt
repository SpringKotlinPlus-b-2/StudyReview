package kotlinassignment.week10.infra.security.exception

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kotlinassignment.week10.infra.security.exception.dto.ErrorResponse
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationEntryPoint: AuthenticationEntryPoint {
    // TODO - 현재 커스텀 예외가 아닌 모든 예외가 이 AuthenticationEntryPoint의 예외로 바꿔치기 당하고 있음
    //  - GlobalExceptionHandler에서 커스텀 예외로 잡지 못한 모든 RuntimeException을 잡은 후 500 에러로 다시 던지도록 임시 조치 해둠
    //  - AuthenticationEntryPoint에 대해서 더 학습할 것

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException,
    ) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "UTF-8"

        ObjectMapper().writeValueAsString(ErrorResponse("JWT 검증이 실패했습니다."))
            .let { response.writer.write(it) }
    }
}
