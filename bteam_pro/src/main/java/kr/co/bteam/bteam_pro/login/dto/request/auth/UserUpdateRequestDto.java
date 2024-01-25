package kr.co.bteam.bteam_pro.login.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
// 회원업데이트 요청을 위한 request class
public class UserUpdateRequestDto {
    // 사용자 id
    @NotBlank
    private String id;
    // pwd이며 pattern을 사용하여 영문자와 숫자를 최소 하나 이상 포함, 길이가 1에서 13문자를 허용
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{1,13}$")
    private String password;

    // 사용자 email
    // @NotBlank
    // @Email
    // private String email;

    // 인증번호
    // @NotBlank
    // private String certificationNumber;

    private String name;
    private String address;
    private String phoneno;
    private String disease;
    private int height;
    private int weight;

}
