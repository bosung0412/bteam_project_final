package kr.co.bteam.bteam_pro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bteam.bteam_pro.dao.WeightDao;
import kr.co.bteam.bteam_pro.vo.WeightVO;

@Service
public class WeightService {
    @Autowired
    private WeightDao dao;

    public List<WeightVO> find() {
        return dao.find();
    }

    // public int insertWeight(WeightVO vo) {
    // return dao.addWeight(vo);
    // }

    public void updateWeight(WeightVO vo) {
        dao.upWeight(vo);
    }

    public WeightVO getHistWeight(int member_no) {
        return dao.getHistWeight(member_no);
    }
}