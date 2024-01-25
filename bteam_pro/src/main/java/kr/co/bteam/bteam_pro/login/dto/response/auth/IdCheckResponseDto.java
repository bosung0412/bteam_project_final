package kr.co.bteam.bteam_pro.login.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import kr.co.bteam.bteam_pro.login.common.ResponseCode;
import kr.co.bteam.bteam_pro.login.common.ResponseMessage;
import kr.co.bteam.bteam_pro.login.dto.response.ResponseDto;
import lombok.Getter;

@Getter
// ID 중복검사에 대한 response class
public class IdCheckResponseDto extends ResponseDto {

    // 부모 생성자 호출
    private IdCheckResponseDto() {
        super();
    }

    // IdCheckResponseDto - ID 중복검사 성공을 했을 시 응답 생성
    public static ResponseEntity<IdCheckResponseDto> success() {
        IdCheckResponseDto responseBody = new IdCheckResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    // ID 중복검사가 실패했을 시 응답 생성
    public static ResponseEntity<ResponseDto> duplicateId() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATE_ID, ResponseMessage.DUPLICATE_ID);
        // 400 에러
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

}
