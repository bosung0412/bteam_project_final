package kr.co.bteam.bteam_pro.login.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bteam.bteam_pro.login.entity.FAQBoard;
import kr.co.bteam.bteam_pro.login.repository.FAQBoardRepository;

@Service
public class FAQBoardService {
    private final FAQBoardRepository faqBoardRepository;

    @Autowired
    public FAQBoardService(FAQBoardRepository faqBoardRepository) {
        this.faqBoardRepository = faqBoardRepository;
    }

    public List<FAQBoard> findAll() {
        return faqBoardRepository.findAll();
    }

    public Optional<FAQBoard> findById(Long id) {
        return faqBoardRepository.findById(id);
    }

    public FAQBoard save(FAQBoard faqBoard) {
        return faqBoardRepository.save(faqBoard);
    }

    public void deleteById(Long id) {
        faqBoardRepository.deleteById(id);
    }
}