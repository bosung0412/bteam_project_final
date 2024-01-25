package kr.co.bteam.bteam_pro.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("faqvo")
@Getter
@Setter
public class FaqBoardVO {
    private int ono;
    private int member_no;
    private String oname;
    private String ocontent;
    private String oregdate;
    private String ocategory;
}
