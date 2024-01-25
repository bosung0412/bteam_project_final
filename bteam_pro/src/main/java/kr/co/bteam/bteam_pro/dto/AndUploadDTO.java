package kr.co.bteam.bteam_pro.dto;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("adto")
@Setter
@Getter
public class AndUploadDTO {
    private int idx;
    private String title;
    private String author;
    private String contents;
    private String imgName;

}
