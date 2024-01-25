package kr.co.bteam.bteam_pro.login.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import kr.co.bteam.bteam_pro.login.common.ResponseCode;
import kr.co.bteam.bteam_pro.login.common.ResponseMessage;
import kr.co.bteam.bteam_pro.login.dto.response.ResponseDto;
import lombok.Getter;

@Getter
// 로그인에 대한 response class
public class SignInResponseDto extends ResponseDto {

    private String token;
    private int expirationTime;

    // 토큰과 만료시간을 초기화
    private SignInResponseDto(String token) {
        super();
        this.token = token;
        this.expirationTime = 3600;
    }

    // 로그인이 성공한 경우에만 token이 전달 (성공 응답 생성 시 호출)
    public static ResponseEntity<SignInResponseDto> success(String token) {
        SignInResponseDto responseBody = new SignInResponseDto(token);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    // 로그인 실패시 호출 실패 응답을 생성
    public static ResponseEntity<ResponseDto> signInFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        // 401 에러
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }

}
