package kr.co.bteam.bteam_pro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.bteam.bteam_pro.service.WeightService;
import kr.co.bteam.bteam_pro.vo.WeightVO;

@RestController // Json형태로 객체 데이터 반환(controller + responseBody)
@RequestMapping("/api/v1/auth")
public class WeightController {
    @Autowired
    private WeightService service;

    @GetMapping("/weightlist")
    public List<WeightVO> listweight() {
        return service.find();
    }

    // @PostMapping("/weightAdd")
    // public int addWeight(@RequestBody WeightVO vo) {
    // System.out.println("============여기 오냐야야");
    // int res = service.insertWeight(vo);
    // System.out.println("===============weightcontrolelr res: "+res);
    // return res;
    // }

    @PostMapping("/upweight")
    public String updateWeight(@RequestBody WeightVO vo) {
        try {
            service.updateWeight(vo);
            return "들어감";
        } catch (Exception e) {
            return "안들어감: " + e.getMessage();
        }
    }
    @GetMapping("/gethistweight")
    public WeightVO getHistWeight(@RequestParam int member_no) {
        return service.getHistWeight(member_no);
    }
}
