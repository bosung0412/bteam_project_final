package kr.co.bteam.bteam_pro.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("nvo")
@Setter
@Getter
public class BoardnextVO {
    private int ono;
    private int nextno;
    private String nextname;
    private String nextdate;
}
