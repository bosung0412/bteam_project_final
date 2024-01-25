package kr.co.bteam.bteam_pro.login.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import kr.co.bteam.bteam_pro.login.common.ResponseCode;
import kr.co.bteam.bteam_pro.login.common.ResponseMessage;
import kr.co.bteam.bteam_pro.login.dto.response.ResponseDto;
import lombok.Getter;

@Getter
// 회원가입에 대한 response class
public class SignUpResponseDto extends ResponseDto {

    private SignUpResponseDto() {
        super();
    }

    // 회원가입 성공 시 성공 응답 호출 및 생성
    public static ResponseEntity<SignUpResponseDto> success() {
        SignUpResponseDto responseBody = new SignUpResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    // 중복 시 실패 시 호출 및 응답 생성
    public static ResponseEntity<ResponseDto> duplicateId() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATE_ID, ResponseMessage.DUPLICATE_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    // 이메일 인증 실패 시 호출 및 응답 생성
    public static ResponseEntity<ResponseDto> certificationFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.CERTIFICATION_FAIL, ResponseMessage.CERTIFICATION_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }

}
