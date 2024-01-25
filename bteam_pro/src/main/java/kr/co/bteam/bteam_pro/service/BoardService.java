package kr.co.bteam.bteam_pro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bteam.bteam_pro.dao.BoardDao;
import kr.co.bteam.bteam_pro.vo.BoardVO;
import kr.co.bteam.bteam_pro.vo.BoardnextVO;
import kr.co.bteam.bteam_pro.vo.BoardprevVO;
import kr.co.bteam.bteam_pro.vo.FaqBoardVO;

@Service
public class BoardService {
    @Autowired
    private BoardDao dao;

    public List<BoardVO> selectBoardList(){
        return dao.selectList();
    }
    public BoardVO selectDetail(int ono){
        dao.updateViews(ono);
        return dao.selectDetail(ono);
    }
    public void updateViews(int ono){
        dao.updateViews(ono);
    }
    public BoardprevVO prevDetail(int ono){
        return dao.prevDetail(ono);
    }
    public BoardnextVO nextDetail(int ono){
        return dao.nextDetail(ono);
    }
    public List<FaqBoardVO> faqList(){
        return dao.faqList();
    }
    public List<FaqBoardVO> faqDetail(String ocategory){
        return dao.faqDetail(ocategory);
    }
}
