package kr.co.bteam.bteam_pro.login.common;

public interface ResponseMessage {

    // 성공 시
    String SUCCESS = "Sucess";
    // 검증 실패시
    String VALIDATION_FAIL = "Validation failed";

    // 아이디 중복 시
    String DUPLICATE_ID = "Duplicate Id";

    // 회원가입 실패 시
    String SIGN_IN_FAIL = "Login information mismathch";

    // 이메일검증 실패시
    String CERTIFICATION_FAIL = "Certifiation failed";

    // 메일 보내기 실패 시
    String MAIL_FAIL = "Mail send failed.";

    // DB에러
    String DATABASE_ERROR = "Database error";
}
