package kr.co.bteam.bteam_pro.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("divo")
@Setter
@Getter
public class DietinfoVO {
    private int diet_id;            // 식단 아이디
    private String img_filename;    // 식단 기록 이미지
    private String mealtime;        // 식사시간
    private String frimg_filename;  // 음식 기록 이미지
    private int nutrient_id;        // 음식 영양소 아이디
    private String name;            // 음식명
    private int weight;             // 중량
    private int cal;                // 칼로리
    private int carbo;              // 탄수화물
    private int sugars;             // 당류
    private int fat;                // 지방
    private int protein;            // 단백질
    private int carcium;            // 칼슘
    private int phosphorus;         // 인
    private int sodium;             // 나트륨
    private int potassium;          // 칼륨
    private int magnesium;          // 마그네슘
    private int iron;               // 철
    private int zinc;               // 아연
    private int cholesterol;        // 콜레스테롤
    private int transfat;           // 트랜스지방
    private String foodtype;        // 음식대표사진
}
