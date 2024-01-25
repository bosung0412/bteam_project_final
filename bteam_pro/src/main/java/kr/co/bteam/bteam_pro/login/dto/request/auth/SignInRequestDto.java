package kr.co.bteam.bteam_pro.login.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
// 로그인 요청 reqeust class
// id, pwd 서버로 전송되는 로그인 요청 데이터를 담는 곳
public class SignInRequestDto {

    // 사용자 id
    @NotBlank
    private String id;

    // 사용자 pwd
    @NotBlank
    private String password;
}
