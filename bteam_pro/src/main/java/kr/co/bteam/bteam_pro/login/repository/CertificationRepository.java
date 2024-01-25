package kr.co.bteam.bteam_pro.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import kr.co.bteam.bteam_pro.login.entity.CertificationEntity;

@Repository
public interface CertificationRepository extends JpaRepository<CertificationEntity, String> {

    // 사용자 아이디 조회
    CertificationEntity findByUserId(String userId);

    // 사용자 아이디 삭제
    @Transactional
    void deleteByUserId(String userId);
}
