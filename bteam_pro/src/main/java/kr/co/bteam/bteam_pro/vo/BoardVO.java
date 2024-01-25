package kr.co.bteam.bteam_pro.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("bvo")
@Setter
@Getter
public class BoardVO {
    private int ono;
    private int member_no;
    private String oname;
    private String ocontent;
    private String oregdate;
    private int views;
}
