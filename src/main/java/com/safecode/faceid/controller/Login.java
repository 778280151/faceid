package com.safecode.faceid.controller;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Login {
    @Autowired
    AipFace aipFace;

    @RequestMapping("/uploadimg")
    public String image(String img) {
        System.out.println(img);
        boolean b = com.safecode.faceid.util.img.GenerateImage(img);
        System.out.println(b);
        return "success";
    }


    @RequestMapping("/jianyan")
    public void jiaoyan() {
        MatchRequest matchRequest1 = new MatchRequest("F:\2018-07-0312.png", "png");
        MatchRequest matchRequest2 = new MatchRequest("F:\2018-07-0312.png", "png");
        List<MatchRequest> list = new ArrayList<>();
        JSONObject match = aipFace.match(list);
        System.out.println(match.toString());
    }
}
