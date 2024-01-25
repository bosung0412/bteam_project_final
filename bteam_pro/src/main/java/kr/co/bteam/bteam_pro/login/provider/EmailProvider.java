package kr.co.bteam.bteam_pro.login.provider;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailProvider {

    private final JavaMailSender javaMailSender;

    // 메일 제목
    private final String SUBJECT = "임상현과 함께 하시겠습니까? (클릭해주세요.)";

    // 이메일을 전송하기 위한 method
    public boolean sendCertificationMail(String email, String certificationNumber) {

        try {
            // imeMessage 및 MimeMessageHelper 클래스를 사용하여 이메일을 구성
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            String htmlContent = getCertificationMesage(certificationNumber);

            // 어디로 메일을 보낼거냐
            messageHelper.setTo(email);
            // 제목
            messageHelper.setSubject(SUBJECT);
            // 메일내용 설정하고 HTML 형식으로 지정
            messageHelper.setText(htmlContent, true);

            javaMailSender.send(message);

        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }

        return true;

    }

    // 전달된 인증 번호를 포함한 HTML 형식의 이메일 내용을 생성
    private String getCertificationMesage(String certificationNumber) {
        String certificationMessage = "";
        certificationMessage += "<h1 style='text-align: center;'>↓↓누르고 임상현과 함께하세요.↓↓";
        certificationMessage += "<h3 style='text-align: center;'> 인증코드 : <strong style='font-size: 32px; letter-spacing: 8px;'>"
                + certificationNumber + "</strong></h3>";
        return certificationMessage;
    }

}
