package kr.co.bteam.bteam_pro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bteam.bteam_pro.dao.AppDao;
import kr.co.bteam.bteam_pro.dto.AndUploadDTO;

@Service
public class AppService {
    @Autowired
    private AppDao dao;

    public int addappImg(AndUploadDTO dto){
        return dao.addappimg(dto);
    }
}
