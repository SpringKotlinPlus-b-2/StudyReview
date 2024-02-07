package kotlinassignment.week10.infra.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher
import org.springframework.web.servlet.handler.HandlerMappingIntrospector

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val authenticationEntryPoint: AuthenticationEntryPoint,
    private val accessDeniedHandler: AccessDeniedHandler,
) {

    @Bean
    fun filterChain(httpSecurity: HttpSecurity, introspector: HandlerMappingIntrospector): SecurityFilterChain {
        return httpSecurity
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .csrf { it.disable() }
            .headers { it.frameOptions { frameOptionsConfig -> frameOptionsConfig.sameOrigin() } }
            .authorizeHttpRequests {
                it.requestMatchers(
                    "/login",
                    "/signup",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/h2-console/**",
                ).permitAll()
                    .requestMatchers( // https://colabear754.tistory.com/170 - Spring Security 사용하면서 H2 콘솔 사용 - TODO 정확한 원리 알아보기
                        MvcRequestMatcher(introspector, "/**").apply { setServletPath("/h2-console") }
                    ).permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java) // TODO 예외 처리
            .exceptionHandling {
                it.authenticationEntryPoint(authenticationEntryPoint)
                // it.accessDeniedHandler(accessDeniedHandler)
            }
            .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder() // 예시 자료와는 다르게 우선 같은 config에 넣어둠, 분리 필요할 경우 분리
}
