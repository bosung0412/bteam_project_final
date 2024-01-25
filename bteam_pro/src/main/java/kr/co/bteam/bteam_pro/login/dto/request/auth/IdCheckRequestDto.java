package kr.co.bteam.bteam_pro.login.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
// 아이디 중복성 검사 request class
// 서버로 전송되는 id를 담고있는 곳
public class IdCheckRequestDto {
    // 필수값이여서 not blank
    @NotBlank
    private String id;
}
