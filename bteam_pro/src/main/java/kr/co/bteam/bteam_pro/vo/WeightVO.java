package kr.co.bteam.bteam_pro.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("wvo")
@Getter
@Setter
public class WeightVO {
    private int log_id;          // 로그 아이디
    private int member_no;       // 회원번호
    private float hist_currentweight; // 현재 몸무게
    private float hist_futureweight;  // 목표 몸무게
    private String change_date;        // 날짜
}
