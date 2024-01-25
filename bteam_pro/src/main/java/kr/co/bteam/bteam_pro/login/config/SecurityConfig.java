package kr.co.bteam.bteam_pro.login.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.bteam.bteam_pro.login.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

// @Bean을 등록가능하게 만들어줌
@Configurable
// bean method를 가지고있다라는뜻
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthenticationFilter;

        @Bean
        protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {

                httpSecurity
                                .cors(cors -> cors
                                                .configurationSource(corsConfigurationSource()))
                                // 사이트 요청
                                .csrf(CsrfConfigurer::disable)
                                // 인증방식을 Bearer로 할껀데 security는 기본적으로 httpBasic이 잡혀있음 그래서 disable시켜줌
                                .httpBasic(HttpBasicConfigurer::disable)
                                // session 방식
                                .sessionManagement(sessionManagement -> sessionManagement
                                                // 세션을 유지하지 않겠다
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                // 권한
                                .authorizeHttpRequests(request -> request
                                                // 이패턴에 대해서는 모두 허용하겠다라는뜻.
                                                .requestMatchers("/", "/api/v1/auth/**", "/oauth2/**", "/api/admin/**").permitAll()
                                                // hasRole을 사용해 권한부여
                                                .requestMatchers("/api/v1/user/**").hasRole("USER")
                                                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                                                .anyRequest().authenticated())
                                // 예외 처리
                                .exceptionHandling(exceptionHandling -> exceptionHandling
                                                .authenticationEntryPoint(new FailedAuthenticationEntryPoint()))
                                // 필터 등록
                                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
                return httpSecurity.build();

        }

        @Bean
        protected CorsConfigurationSource corsConfigurationSource() {

                CorsConfiguration corsconfiguration = new CorsConfiguration();
                // 모든 출처에서 허용
                corsconfiguration.addAllowedOrigin("*");
                corsconfiguration.addAllowedMethod("*");
                corsconfiguration.addAllowedHeader("*");

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", corsconfiguration);

                return source;
        }
}

// 인가처리에 실패했을 시
class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint {

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response,
                        AuthenticationException authException) throws IOException, ServletException {

                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                // {"code": "NP", "message" : "No Permissin."}
                response.getWriter().write("{\"code\": \"NP\", \"message\" : \"No Permissin.\"}");
        }

}
