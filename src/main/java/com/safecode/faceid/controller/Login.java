package com.safecode.faceid.controller;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import com.safecode.faceid.test.Face;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    @ResponseBody
    public String jiaoyan() {
        Face face = new Face();
        face.zhuyao();
        return  "success";
    }

    @RequestMapping("/reg")
    public String ret() {
        return "reg";
    }
}
