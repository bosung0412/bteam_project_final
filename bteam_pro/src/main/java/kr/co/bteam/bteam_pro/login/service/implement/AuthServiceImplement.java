package kr.co.bteam.bteam_pro.login.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.bteam.bteam_pro.login.common.CertificationNumber;
import kr.co.bteam.bteam_pro.login.dto.request.auth.CheckCertificationRequestDto;
import kr.co.bteam.bteam_pro.login.dto.request.auth.EmailCertificationRequestDto;
import kr.co.bteam.bteam_pro.login.dto.request.auth.IdCheckRequestDto;
import kr.co.bteam.bteam_pro.login.dto.request.auth.SignInRequestDto;
import kr.co.bteam.bteam_pro.login.dto.request.auth.SignUpRequestDto;
import kr.co.bteam.bteam_pro.login.dto.request.auth.UserUpdateRequestDto;
import kr.co.bteam.bteam_pro.login.dto.response.ResponseDto;
import kr.co.bteam.bteam_pro.login.dto.response.auth.CheckCertificationResponseDto;
import kr.co.bteam.bteam_pro.login.dto.response.auth.EmailCertificationResponseDto;
import kr.co.bteam.bteam_pro.login.dto.response.auth.IdCheckResponseDto;
import kr.co.bteam.bteam_pro.login.dto.response.auth.SignInResponseDto;
import kr.co.bteam.bteam_pro.login.dto.response.auth.SignUpResponseDto;
import kr.co.bteam.bteam_pro.login.dto.response.auth.UserUpdateResponseDto;
import kr.co.bteam.bteam_pro.login.entity.CertificationEntity;
import kr.co.bteam.bteam_pro.login.entity.UserEntity;
import kr.co.bteam.bteam_pro.login.provider.EmailProvider;
import kr.co.bteam.bteam_pro.login.provider.JwtProvider;
import kr.co.bteam.bteam_pro.login.repository.CertificationRepository;
import kr.co.bteam.bteam_pro.login.repository.UserRepository;
import kr.co.bteam.bteam_pro.login.service.AuthService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
// AuthService의 구현체
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;

    private final CertificationRepository certificationRepository;

    private final JwtProvider jwtProvider;
    private final EmailProvider emailProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 사용자 아이디 중복 체크
    // 전송된 IdCheckRequestDto를 받아 아이디가 존재하는지 여부 확인
    @Override
    public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto) {
        try {
            // 중복 시, IdCheckResponseDto 안에 duplicatedId메소드 이용하여 중복 응답 생성 및 반환
            String userId = dto.getId();
            boolean isExistId = userRepository.existsByUserId(userId);
            if (isExistId)
                return IdCheckResponseDto.duplicateId();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        // 성공 시, 성공 응답 생성 및 반환
        return IdCheckResponseDto.success();
    }

    // 이메일 인증을 처리하는 메서드
    // 전송된 EmailCertificationRequestDto를 받아서 이메일로 인증메일 전송 및 , db에 사용자 인증 정보 저장
    @Override
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto) {

        try {
            String userId = dto.getId();
            String email = dto.getEmail();

            // 중복 발생시 처리
            boolean isExistId = userRepository.existsByUserId(userId);
            if (isExistId)
                return EmailCertificationResponseDto.duplicateId();

            String certificationNumber = CertificationNumber.getCertificationNumber();
            // 인증번호가 안맞을시 처리
            boolean isSuccessed = emailProvider.sendCertificationMail(email, certificationNumber);
            if (!isSuccessed)
                return EmailCertificationResponseDto.mailSendFail();
            // db에 저장
            CertificationEntity certificationEntity = new CertificationEntity(userId, email, certificationNumber);
            certificationRepository.save(certificationEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        // 성공 응답 생성 및 반환
        return EmailCertificationResponseDto.success();
    }

    // 사용자가 입력한 이메일과 인증번호가 일치하는지 확인 메서드
    // 전송된 CheckCertificationRequestDto를 받아 이메일과 인증번호가 일치하는지 확인
    @Override
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto) {

        try {

            String userId = dto.getId();
            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();
            // UserId에 있지않은 email일시 반환 - fail
            CertificationEntity certificationEntity = certificationRepository.findByUserId(userId);
            if (certificationEntity == null)
                return CheckCertificationResponseDto.certificationFail();
            // 인증번호가 맞지않을시 반환 - fail
            boolean isMatched = certificationEntity.getEmail().equals(email)
                    && certificationEntity.getCertificationNumber().equals(certificationNumber);
            if (!isMatched)
                return CheckCertificationResponseDto.certificationFail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        // 성공 응답 생성 및 반환
        return CheckCertificationResponseDto.success();
    }

    // 사용자의 회원가입 처리 메서드
    // 전송된 SignUpRequestDto를 받아 아이디 중복 여부, 인증번호 일치, db에 저장
    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
        try {
            // 중복 확인
            String userId = dto.getId();
            boolean isExistId = userRepository.existsByUserId(userId);
            // 실패 시 duplicateId반환
            if (isExistId)
                return SignUpResponseDto.duplicateId();

            // 이메일 확인 및 중복확인
            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();

            CertificationEntity certificationEntity = certificationRepository.findByUserId(userId);
            boolean isMatched = certificationEntity.getEmail().equals(email) &&
                    certificationEntity.getCertificationNumber().equals(certificationNumber);
            if (!isMatched)
                return SignUpResponseDto.certificationFail();

            // 암호화된 코드로 저장
            String password = dto.getPassword();
            String encodePassword = passwordEncoder.encode(password);
            dto.setPassword(encodePassword);

            // db저장
            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

            // 삭제
            // certificationRepository.delete(certificationEntity);
            certificationRepository.deleteByUserId(userId);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignUpResponseDto.success();
    }

    // 사용자 로그인을 처리하는 메서드
    // 전송된 SignInRequestDto을 받아 아이디, 비밀번호 일치확인, 일치 시, jwt토큰 생성 및 반환
    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

        String token = null;

        try {
            // 사용자 조회
            String userId = dto.getId();
            UserEntity userEntity = userRepository.findByUserId(userId);
            // 조회된 사용자가 없을 시 fail반환
            if (userEntity == null)
                return SignInResponseDto.signInFail();

            // 전송된 로그인 요청에서 비밀번호를 추출
            String password = dto.getPassword();
            // 데이터베이스에서 조회한 사용자의 암호화된 비밀번호를 가져옴
            String encodedPassword = userEntity.getPassword();
            // 입력된 비밀번호와 데이터베이스의 비밀번호를 비교하여 일치 여부를 확인
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if (!isMatched)
                return SignInResponseDto.signInFail();
            // 로그인 성공시, JwtProvider를 사용해서 jwt토큰 생성(userId)
            token = jwtProvider.create(userId);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        // 성공 응답 생성 및 생성된 jwt토큰 반환
        return SignInResponseDto.success(token);
    }

    // 사용자의 회원 정보 업데이트 메서드
    // 전송된 UserUpdateRequestDto를 받아 인증번호 일치, db에 저장
    @Override
    public ResponseEntity<? super UserUpdateResponseDto> userUpdate(UserUpdateRequestDto dto) {
        try {
            // String userId = dto.getId();

            // 암호화된 코드로 저장
            String password = dto.getPassword();
            String encodePassword = passwordEncoder.encode(password);
            dto.setPassword(encodePassword);

            // db 저장
            UserEntity userUpdateEntity = userRepository.findByUserId(dto.getId());
            if (userUpdateEntity != null) {
                userRepository.save(userUpdateEntity);
            } else {
                UserUpdateResponseDto.databaseError();
                System.out.println("==========에러다....");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return UserUpdateResponseDto.success();
    }

    // 회원 정보 조회
    @Override
    public ResponseEntity<? super UserUpdateResponseDto> userSelect(UserUpdateRequestDto dto) {
        try {
            UserEntity userEntity = userRepository.findByUserId(dto.getId());
            if (userEntity != null) {
                userRepository.save(userEntity);
            } else {
                UserUpdateResponseDto.databaseError();
                System.out.println("==========에러다....");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return UserUpdateResponseDto.success();

    }

}
