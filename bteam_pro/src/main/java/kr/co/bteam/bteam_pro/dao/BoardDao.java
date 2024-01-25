package kr.co.bteam.bteam_pro.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.bteam.bteam_pro.vo.BoardVO;
import kr.co.bteam.bteam_pro.vo.BoardnextVO;
import kr.co.bteam.bteam_pro.vo.BoardprevVO;
import kr.co.bteam.bteam_pro.vo.FaqBoardVO;

@Mapper
public interface BoardDao {
    public List<BoardVO> selectList();
    public BoardVO selectDetail(int ono);
    public void updateViews(int ono);
    public BoardprevVO prevDetail(int ono);
    public BoardnextVO nextDetail(int ono);
    public List<FaqBoardVO> faqList();
    public List<FaqBoardVO> faqDetail(String ocategory);
}
