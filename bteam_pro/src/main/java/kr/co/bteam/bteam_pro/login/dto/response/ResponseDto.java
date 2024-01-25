package kr.co.bteam.bteam_pro.login.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import kr.co.bteam.bteam_pro.login.common.ResponseCode;
import kr.co.bteam.bteam_pro.login.common.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
// response - auth 안에 클래스들이 모두 implements 되어있다
public class ResponseDto {
    private String code;
    private String message;

    // 성공 응답을 나타내는 생성자
    public ResponseDto() {
        this.code = ResponseCode.SUCCESS;
        this.message = ResponseMessage.SUCCESS;
    }

    // DB관련 오류가 발생했을 때 반환되는 응답을 생성
    public static ResponseEntity<ResponseDto> databaseError() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        // 500 에러
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

    // 유효성 검사 실패 시 반환되는 응답을 생성
    public static ResponseEntity<ResponseDto> validationFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        // 400에러
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
