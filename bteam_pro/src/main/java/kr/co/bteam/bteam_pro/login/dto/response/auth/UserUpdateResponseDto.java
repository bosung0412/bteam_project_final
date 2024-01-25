package kr.co.bteam.bteam_pro.login.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import kr.co.bteam.bteam_pro.login.dto.response.ResponseDto;
import lombok.Getter;

@Getter
public class UserUpdateResponseDto extends ResponseDto {
    public UserUpdateResponseDto() {
        super();
    }

    // 회원 업데이트 성공 시 성공 응답 호출 및 생성
    public static ResponseEntity<UserUpdateResponseDto> success() {
        UserUpdateResponseDto responseBody = new UserUpdateResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
