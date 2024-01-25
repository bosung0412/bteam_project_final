package kr.co.bteam.bteam_pro.login.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.bteam.bteam_pro.login.dto.request.auth.SignUpRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
// jpa 엔터티임
@Entity(name = "certification")
// 테이블 이름 지정
@Table(name = "certification")
// 이메일 인증 관리를 위한 entity
public class CertificationEntity {
    // 기본 키
    @Id
    // 고유 식별자
    private String userId;
    // 이메일 주소
    private String email;
    // 이메일 인증에 사용되는 인증번호를 저장하는 필드
    private String certificationNumber;

    public CertificationEntity(SignUpRequestDto dto) {
        this.userId = dto.getId();
        this.email = dto.getEmail();
        this.certificationNumber = dto.getCertificationNumber();
    }

}
