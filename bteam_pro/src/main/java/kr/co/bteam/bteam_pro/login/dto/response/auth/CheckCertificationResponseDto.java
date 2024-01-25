package kr.co.bteam.bteam_pro.login.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import kr.co.bteam.bteam_pro.login.dto.response.ResponseDto;
import lombok.Getter;

@Getter
// 이메일 인증번호 확인에 대한 응답을 생성하는 response class
public class CheckCertificationResponseDto extends ResponseDto {

    private CheckCertificationResponseDto() {
        super();
    }

    // ResponseEntity - HTTP 응답 상태 코드
    // CheckCertificationResponseDto - 이메일 인증번호확인에 대한 성공 or 실패에 대한 코드 생성
    public static ResponseEntity<CheckCertificationResponseDto> success() {
        CheckCertificationResponseDto responseBody = new CheckCertificationResponseDto();
        // OK는 200 성공 응답
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    // 이메일 인증번호확인이 실패하면 코드 생성
    public static ResponseEntity<ResponseDto> certificationFail() {
        ResponseDto responseBody = new ResponseDto();
        // UNAUTHORIZED 401 실패 응답
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }

}
