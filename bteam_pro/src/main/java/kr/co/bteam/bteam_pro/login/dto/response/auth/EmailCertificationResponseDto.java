package kr.co.bteam.bteam_pro.login.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import kr.co.bteam.bteam_pro.login.common.ResponseCode;
import kr.co.bteam.bteam_pro.login.common.ResponseMessage;
import kr.co.bteam.bteam_pro.login.dto.response.ResponseDto;
import lombok.Getter;

@Getter
// 이메일 인증에 대한 응답을 생성해 주는 response class
public class EmailCertificationResponseDto extends ResponseDto {

    private EmailCertificationResponseDto() {
        super();
    }

    // ResponseEntity - HTTP 응답 상태 코드
    // EmailCertificationResponseDto - 이메일 인증에 대한 성공 응답
    public static ResponseEntity<EmailCertificationResponseDto> success() {
        EmailCertificationResponseDto responseBody = new EmailCertificationResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    // duplicateId - 중복된 아이디로 이메일 인증을 시도했을 시 호출
    public static ResponseEntity<ResponseDto> duplicateId() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATE_ID, ResponseMessage.DUPLICATE_ID);
        // BAD_REQUEST - 400 실패 에러
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    // 이메일 전송 실패 했을 시 응답
    public static ResponseEntity<ResponseDto> mailSendFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATE_ID, ResponseMessage.DUPLICATE_ID);
        // INTERNAL_SERVER_ERROR - 500 실패 에러
        System.out.println(
                "==========여기 오는거니 이메일 전송 실패!!!!: EmailCertificationResponseDto mailSendFail: " + responseBody);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

}
