package kr.co.bteam.bteam_pro.login.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
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
import kr.co.bteam.bteam_pro.login.service.AuthService;
import kr.co.bteam.bteam_pro.login.service.UserService;
import lombok.RequiredArgsConstructor;

// restful controller
@RestController
@RequestMapping("/api/v1/auth")
// 생성자 주입을 위한
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final UserService userservice;

    // id 중복성 체크
    @PostMapping("/id-check")
    public ResponseEntity<? super IdCheckResponseDto> idCheck(
            // IdCheckRequestDto를 통해 아이디 중복성체크 및 데이터 유효성 검사
            @RequestBody @Valid IdCheckRequestDto requestBody) {
        ResponseEntity<? super IdCheckResponseDto> response = authService.idCheck(requestBody);
        return response;
    }

    // 이메일 요청
    @PostMapping("/email-certification")
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(
            // EmailCertificationResponseDto 대한 이메일 인증 요청 및 데이터 유효성 검사
            @RequestBody @Valid EmailCertificationRequestDto requestBody) {
        ResponseEntity<? super EmailCertificationResponseDto> response = authService.emailCertification(requestBody);
        return response;
    }

    // email 인증번호 체크
    @PostMapping("/check-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(
            // CheckCertificationResponseDto 대한 이메일 인증번호 체크 및 데이터 유효성검사
            @RequestBody @Valid CheckCertificationRequestDto requestBody) {
        ResponseEntity<? super CheckCertificationResponseDto> response = authService.checkCertification(requestBody);

        return response;
    }

    // 회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(
            // SignUpResponseDto 통해 회원 가입 요청 및 데이터 유효성 검사
            @RequestBody @Valid SignUpRequestDto requestBody) {
        ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(
            // SignInResponseDto 통해 로그인 요청 및 데이터 유효성 검사
            @RequestBody @Valid SignInRequestDto requestBody) {
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);

        return response;
    }

    // 회원 업데이트
    @PatchMapping("/update/{userId}")
    public ResponseEntity<? super UserUpdateResponseDto> update(@RequestBody @Valid UserUpdateRequestDto requestBody) {
        System.out.println("============여기 오니..AuthController update");
        ResponseEntity<? super UserUpdateResponseDto> response = authService.userUpdate(requestBody);
        return response;
    }

    // 회원 조회
    @GetMapping("/getMember/{userId}")
    public ResponseEntity<? super UserUpdateResponseDto> getDate(@RequestBody @Valid UserUpdateRequestDto requestBody) {
        System.out.println("==============여기 오니: AuthController getDate ");
        ResponseEntity<? super UserUpdateResponseDto> response = authService.userSelect(requestBody);
        return response;
    }

    // 질문 형식
    @GetMapping("/getquestion")
    public List<QuestionItem> getQuestion() {
        System.out.println("========여기 오니");
        return Arrays.asList(new QuestionItem(0, "내가 가장 빼고 싶은 곳은?"),
                new QuestionItem(1, "내가 가장 자신있는 곳은?"),
                new QuestionItem(2, "가장 좋아하는 음식은?"));
    }

    public class QuestionItem {
        @JsonProperty("id")
        private int id;
        @JsonProperty("question")
        private String question;

        public QuestionItem(int id, String question) {
            this.id = id;
            this.question = question;
            System.out.println("========여기 question: " + question);
        }
    }


    @GetMapping("/getuserheight/{userId}")
    public ResponseEntity<Integer> getHeight(@PathVariable String userId) {
        Integer height = userservice.getHeight(userId);
        return new ResponseEntity<>(height, HttpStatus.OK);
    }

    @GetMapping("/findIdByEmail")
    public ResponseEntity<String> findByEmail(@RequestParam String email) {
        String userId = userservice.findByEmail(email);
        System.out.println("sssssssssqoiwejiodwadjiofjadsiofjsdokfjlaksjfvas");

        if (userId != null) {
            return new ResponseEntity<>(userId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
}
