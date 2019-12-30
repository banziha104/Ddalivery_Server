package com.lyj.ddalivery.ddalivery.config

import lombok.RequiredArgsConstructor
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
//    @Bean
//    @Throws(Exception::class)
//    override fun authenticationManagerBean(): AuthenticationManager {
//        println("=================ok2=======================")
//        return super.authenticationManagerBean()
//    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .httpBasic().disable() // rest api 이므로 기본설정 사용안함..
                .csrf().disable() // csrf  disable처리.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt token으로 인증하므로 세션은 필요없으므로 생성안함.
                .and()
                .authorizeRequests()
                .antMatchers("/*/login").permitAll()
                .antMatchers("/exception/**", "/actuator/health").permitAll() //.anyRequest().hasRole("ROLE_USER") // 그외 나머지 요청은 모두 인증된 회원만 접근 가능
// .and()
//.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
//.and()
//     .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
//                .and()
//                .addFilterBefore(JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter::class.java) // jwt token 필터를 id/password 인증 필터 전에 넣는다

    }

    // Spring RestDoc 인증 없이
    override fun configure(web: WebSecurity) {
        web.ignoring().mvcMatchers("/docs/index.html")
        web.ignoring().mvcMatchers(HttpMethod.GET, "/docs/**")
        web.ignoring().antMatchers(*ignorePages)
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.addAllowedOrigin("*")
        configuration.addAllowedMethod("*")
        configuration.addAllowedHeader("*")
        configuration.allowCredentials = true
        configuration.maxAge = 3600L
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/api/**", configuration)
        return source
    }

    companion object {
        //@NonNull
//private final PasswordEncoder passwordEncoder;
        private val ignorePages = arrayOf(
                "/api/**"
        )
    }
}