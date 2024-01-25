package kr.co.bteam.bteam_pro.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.bteam.bteam_pro.login.entity.FAQBoard;
import kr.co.bteam.bteam_pro.login.service.FAQBoardService;

@RestController
@RequestMapping("/api/admin/faqboard")
public class FAQBoardController {

    private final FAQBoardService faqBoardService;

    @Autowired
    public FAQBoardController(FAQBoardService faqBoardService) {
        this.faqBoardService = faqBoardService;
    }

    // 리스트 띄우기
    @GetMapping
    public List<FAQBoard> getAllFAQBoards() {
        return faqBoardService.findAll();
    }

    // 아이디로 공지사항 찾기
    @GetMapping("/{id}")
    public FAQBoard getFAQBoardById(@PathVariable Long id) {
        return faqBoardService.findById(id).orElse(null);
    }

    // 좀더 확인해보기 일단은 잘 되는중
    @PostMapping
    public FAQBoard createFAQBoard(@RequestBody FAQBoard faqBoard) {
        return faqBoardService.save(faqBoard);
    }

    // update
    @PutMapping("/{id}")
    public FAQBoard updateFAQBoard(@PathVariable Long id, @RequestBody FAQBoard faqBoardDetails) {
        FAQBoard faqBoard = faqBoardService.findById(id).orElse(null);
        if (faqBoard != null) {
            // Update the fields
            faqBoard.setOname(faqBoardDetails.getOname());
            faqBoard.setOcontent(faqBoardDetails.getOcontent());
            faqBoard.setOcategory(faqBoardDetails.getOcategory());
            faqBoard.setOcentent(faqBoardDetails.getOcentent());
            faqBoardService.save(faqBoard);
        }
        return faqBoard;
    }

    // 삭제
    @DeleteMapping("/{id}")
    public void deleteFAQBoard(@PathVariable Long id) {
        faqBoardService.deleteById(id);
    }
}