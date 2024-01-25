package kr.co.bteam.bteam_pro.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.co.bteam.bteam_pro.dto.AndUploadDTO;

@Mapper
public interface AppDao {

    public int addappimg(AndUploadDTO dto);
}
