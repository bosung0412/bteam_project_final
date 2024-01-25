package kr.co.bteam.bteam_pro.login.provider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
public class JwtProvider {

    // properties의 secret-key값 가져옴
    @Value("${secret-key}")
    private String secretKey;

    // jwt 생성 메소드
    public String create(String userId) {

        // jwt토큰 만료시간을 1시간으로 설정.
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        String jwt = Jwts.builder()
                // signwith key부분이 encoded 마지막 부분 jwt의signature부분
                .signWith(key, SignatureAlgorithm.HS256)
                // setsubject가 payload첫부분에서 ~~
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .compact();

        return jwt;
    }

    // jwt검증
    public String validate(String jwt) {
        // 결과를 받음 성공하면 subject에 사용자 아이디 저장
        String subject = null;
        // jwt 검증할때 사용됨, jwt생성할때 사용한 키와 일치해야함
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        try {

            subject = Jwts.parserBuilder()
                    // 검증에 사용할 키
                    .setSigningKey(key)
                    .build()
                    // jwt를 검증, 검증 성공 시 클레임 반환
                    .parseClaimsJws(jwt)
                    // jwt 바디의 클레임을 가져옴
                    .getBody()
                    // claim에서 사용자 아이디를 가져옴
                    .getSubject();

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return null;
    }
}
