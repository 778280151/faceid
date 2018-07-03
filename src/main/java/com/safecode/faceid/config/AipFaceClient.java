package com.safecode.faceid.config;

import com.baidu.aip.face.AipFace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AipFaceClient {
    /**
     * 百度人脸识别api实例
     * By zhangchongjie
     *
     * @date
     * @return返回并创建一个百度的apiface实例对象
     */
    @Bean
    public AipFace aipFace() {
//        final String APP_ID = "9802974";
//        final String API_KEY = "OVYw5Ok0y9U8n6CfVPYt0wfZ";
//        final String SECRET_KEY = "ce325705062f4379b38a5ae20af8f092";
        final String APP_ID = "11478275";
        final String API_KEY = "4jU5O16wo0pgu3pNb3MrDzei";
        final String SECRET_KEY = "xLhGsdnRGg7qwjdpCD6xY1RQK2iFjU8G";
        final AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
        return client;
    }
}
