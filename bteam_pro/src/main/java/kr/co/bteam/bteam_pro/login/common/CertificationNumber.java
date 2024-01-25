package kr.co.bteam.bteam_pro.login.common;

public class CertificationNumber {
    public static String getCertificationNumber() {

        String certificationNumber = "";

        // 0부터 9까지의 숫자를 랜덤으로 인증번호
        for (int count = 0; count < 4; count++)
            certificationNumber += (int) (Math.random() * 10);
        return certificationNumber;
    }
}
