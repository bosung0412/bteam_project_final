package kr.co.bteam.bteam_pro.login.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.bteam.bteam_pro.login.entity.UserEntity;
import kr.co.bteam.bteam_pro.login.provider.JwtProvider;
import kr.co.bteam.bteam_pro.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
// 필터를 사용하기위해 추상메서드 확장을 해줘야함
// 확장메서드를 써주면 JwtAuthenticationFilter에서 quick fix하면 dofilterinternal이 만들어짐
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {

            String token = parseBearerToken(request);
            if (token == null) {
                // 만약 토큰이 없으면 밑에 작업을진행하지말고 다음 필터로 넘겨라
                // 없는경우는 authrization, Bearer토큰이 없을 시
                filterChain.doFilter(request, response);
                return;
            }
            // 검증은 userId로 하는데 null일시 필터 넘기기
            String userId = jwtProvider.validate(token);
            if (userId == null) {
                filterChain.doFilter(request, response);
                return;
            }

            // 검증까지 완료시 유저정보 꺼내오기
            UserEntity userEntity = userRepository.findByUserId(userId);
            String role = userEntity.getRole(); // role : ROLE_USER, ROLE_ADMIN

            System.out.println(role);

            // 권한을 배열로 저장
            List<GrantedAuthority> authorities = new ArrayList<>();
            // ROLE_로 들어가야됨
            authorities.add(new SimpleGrantedAuthority(role));

            // 빈 컨텍스트 하나 생성
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

            AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, null,
                    authorities);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            securityContext.setAuthentication(authenticationToken);
            SecurityContextHolder.setContext(securityContext);
            ;

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        // 다음필터로 넘어가는 과정
        filterChain.doFilter(request, response);

    }

    private String parseBearerToken(HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");

        // hastext : 0이거나 화이트 스페이스인지 아닌지 검사해주는것
        boolean hasAuthorization = StringUtils.hasText(authorization);
        // 문자로 구성되어있는지 아니면 null
        if (!hasAuthorization)
            return null;

        // 인증방식이 Bearer^인지를 확인
        boolean isBearer = authorization.startsWith("Bearer ");
        if (!isBearer)
            return null;

        // bearer^뒤부터 가져온다
        String token = authorization.substring(7);
        return token;

    }

}
