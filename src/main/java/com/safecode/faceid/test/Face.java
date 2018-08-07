package com.safecode.faceid.test;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import com.safecode.faceid.util.Img2base64;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author 人脸识别
 * Date:2018.7.3
 * @see Img2base64 img图片和base64转换工具类
 * @see
 */

public class Face {
    static final String APP_ID = "11478275";
    static final String API_KEY = "4jU5O16wo0pgu3pNb3MrDzei";
    static final String SECRET_KEY = "xLhGsdnRGg7qwjdpCD6xY1RQK2iFjU8G";

    public  void zhuyao() {
        // 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        Face f = new Face();
//        f.sample(client);
        f.facecanshu(client);
    }

    public void sample(AipFace client) {
        String image1 = "https://desk-fd.zol-img.com.cn/t_s208x130c5/g5/M00/0F/08/ChMkJlauy7qIWRiRAF44Nks5EpkAAH84AJfgiIAXjhO164.png";
        String image2 = "https://desk-fd.zol-img.com.cn/t_s208x130c5/g5/M00/0F/08/ChMkJlauzGuIDo5-AAmEKbHNhzwAAH89wIKvi8ACYRB721.jpg";
        String image3 = Img2base64.getImgStr("F:/jay1.jpg");
        String image4 = Img2base64.getImgStr("F:/jay2.jpeg");
        // image1/image2也可以为url或facetoken, 相应的imageType参数需要与之对应。
        MatchRequest req1 = new MatchRequest(image3, "BASE64", "LIVE", "NONE", "NONE");
        MatchRequest req2 = new MatchRequest(image4, "BASE64");
        ArrayList<MatchRequest> requests = new ArrayList<MatchRequest>();
        requests.add(req1);
        requests.add(req2);
        JSONObject res = client.match(requests);
        Double jiaoyanzhi = Double.parseDouble(res.getJSONObject("result").get("score").toString());
        System.out.println(jiaoyanzhi);
        System.out.println(res.toString(2));
    }

    /**
     * show
     *
     * @param client 百度人脸识别api实体类
     * @date new {@link java.util.Date}
     */
    public void facecanshu(AipFace client) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("face_field", "age,beauty");
        options.put("max_face_num", "10");
        options.put("face_type", "LIVE");
        System.out.println(options.size());
        String image = Img2base64.getImgStr("F:/jay2.jpeg");
        String imageType = "BASE64";

        // 人脸检测
        JSONObject res = client.detect(image, imageType, options);
        System.out.println(res.toString(2));
    }

}
