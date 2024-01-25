package kr.co.bteam.bteam_pro.login.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
// 이메일 인증 요청에 따른 request class
// // 서버로 전송되는 아이디, 이메일 데이터를 담기 위한 곳
public class EmailCertificationRequestDto {

    // id 비어있으면 안됨
    @NotBlank
    private String id;

    // email 비어있으면 안됨
    @Email
    @NotBlank
    private String email;
}
