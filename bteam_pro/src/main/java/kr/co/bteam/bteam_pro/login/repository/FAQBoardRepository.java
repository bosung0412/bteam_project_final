package kr.co.bteam.bteam_pro.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.bteam.bteam_pro.login.entity.FAQBoard;

public interface FAQBoardRepository extends JpaRepository<FAQBoard, Long> {
    
}
