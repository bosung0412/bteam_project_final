package kr.co.bteam.bteam_pro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.bteam.bteam_pro.service.BoardService;
import kr.co.bteam.bteam_pro.vo.BoardVO;
import kr.co.bteam.bteam_pro.vo.BoardnextVO;
import kr.co.bteam.bteam_pro.vo.BoardprevVO;
import kr.co.bteam.bteam_pro.vo.FaqBoardVO;

@RestController
@RequestMapping("/api/v1/auth")
public class BoardController {
    @Autowired
    private BoardService service;

    @GetMapping("/boardList")
    public List<BoardVO> listboard(){
        return service.selectBoardList();
    }
    @RequestMapping("/boardDetail")
    public BoardVO detailBoard(@RequestParam int ono){
        return service.selectDetail(ono);
    }
    @PostMapping(value = "/boardView")
    public void updateViews(@RequestParam int ono){
        service.updateViews(ono);
    }
    @RequestMapping("/prevDetail")
    public BoardprevVO prevBoard(@RequestParam int ono){
        return service.prevDetail(ono);
    }
    @RequestMapping("/nextDetail")
    public BoardnextVO nextBoard(@RequestParam int ono){
        return service.nextDetail(ono);
    }
    @GetMapping("/faqBoard")
    public List<FaqBoardVO> faqList(){
        return service.faqList();
    }
    @GetMapping("/faqDetail")
    public List<FaqBoardVO> faqDetail(@RequestParam String ocategory){
        return service.faqDetail(ocategory);
    }
}