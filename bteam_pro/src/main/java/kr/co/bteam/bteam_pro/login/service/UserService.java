package kr.co.bteam.bteam_pro.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.co.bteam.bteam_pro.login.entity.UserEntity;
import kr.co.bteam.bteam_pro.login.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 사용자 목록 조회 메서드
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    // 사용자 삭제 메서드
    public void deleteUser(String userId) {
        // 사용자가 존재하는지 확인
        if (userRepository.existsByUserId(userId)) {
            userRepository.deleteByUserId(userId);
        } else {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + userId);
        }
    }

    public String findByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        return (userEntity != null) ? userEntity.getUserId() : null;
    }


    // 표준 칼로리 계산용 키 조회
    public Integer getHeight(String userId) {
        return userRepository.getHeightByUserId(userId);
    }
}