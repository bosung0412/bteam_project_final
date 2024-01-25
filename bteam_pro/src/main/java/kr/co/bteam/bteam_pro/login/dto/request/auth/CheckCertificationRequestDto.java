package kr.co.bteam.bteam_pro.login.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
// 이메일 인증번호 확인하는 request class
// 서버로 전송되는 아이디, 이메일, 인증번호 데이터를 담기위한 것
public class CheckCertificationRequestDto {

    // 사용자 id이고 비어있으면 안됨
    @NotBlank
    private String id;

    // 사용자 email이고 비어있으면 안됨
    @Email
    @NotBlank
    private String email;

    // 사용자가 입력한인증번호이며 비어있으면 안됨
    @NotBlank
    private String certificationNumber;
}
