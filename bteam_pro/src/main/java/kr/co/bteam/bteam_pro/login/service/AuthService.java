package kr.co.bteam.bteam_pro.login.service;

import org.springframework.http.ResponseEntity;

import kr.co.bteam.bteam_pro.login.dto.request.auth.CheckCertificationRequestDto;
import kr.co.bteam.bteam_pro.login.dto.request.auth.EmailCertificationRequestDto;
import kr.co.bteam.bteam_pro.login.dto.request.auth.IdCheckRequestDto;
import kr.co.bteam.bteam_pro.login.dto.request.auth.SignInRequestDto;
import kr.co.bteam.bteam_pro.login.dto.request.auth.SignUpRequestDto;
import kr.co.bteam.bteam_pro.login.dto.request.auth.UserUpdateRequestDto;
import kr.co.bteam.bteam_pro.login.dto.response.auth.CheckCertificationResponseDto;
import kr.co.bteam.bteam_pro.login.dto.response.auth.EmailCertificationResponseDto;
import kr.co.bteam.bteam_pro.login.dto.response.auth.IdCheckResponseDto;
import kr.co.bteam.bteam_pro.login.dto.response.auth.SignInResponseDto;
import kr.co.bteam.bteam_pro.login.dto.response.auth.SignUpResponseDto;
import kr.co.bteam.bteam_pro.login.dto.response.auth.UserUpdateResponseDto;

// 인증관련 메소드
public interface AuthService {
    // 부모형태도 가져올 수 있게 super
    // 사용자 중복체크하는 메서드 클라이언트에서 요청한 IdCheckRequestDto 아이디 중복확인 응답 생성 및 반환
    ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);

    // 이메일 인증 요청 메소드 클라이언트에서 요청한 EmailCertificationRequestDto 이메일로 인증 메일을 전송하고 응답 생성
    // 및 반환
    ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto);

    // 인증번호 확인 메서드 클라이언트에서 요청한 CheckCertificationRequestDto를 이메일의 인증번호를 확인하고 응답을 생성
    // 및 반환
    ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto);

    // 사용자 회원가입 처리 메서드 클라이언트에서 요청한 SignUpRequestDto를 받아서 호원가입시도하고 응답 생성 및 반환
    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);

    // 사용자 로그인 메서드 클라이언트에서 요청한 SignInRequestDto 를 받아서 시도하고 응답 생성 및 반환
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);

    // 사용자 회원 정보 업데이트 처리 메서드 UserUpdateRequestDto를 받아서 시도
    ResponseEntity<? super UserUpdateResponseDto> userUpdate(UserUpdateRequestDto dto);

    // 사용자 회원 정보 가져오기
    ResponseEntity<? super UserUpdateResponseDto> userSelect(UserUpdateRequestDto dto);

}
