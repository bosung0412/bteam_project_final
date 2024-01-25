package kr.co.bteam.bteam_pro.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.bteam.bteam_pro.vo.WeightVO;

// mapper.xml
// namespace = "kr.co.bteam.bteam_pro.dao.WeightDao"
// MyBatis에 사용되는 추상메서드
@Mapper
public interface WeightDao {
    // mapper.xml에 추상메서드 이름 => id="find"
    public List<WeightVO> find();

    // public int addWeight(WeightVO vo);

    public void upWeight(WeightVO vo);

    public WeightVO getHistWeight(int member_no);
}
