package com.safecode.faceid.util;

import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class img {
    /**
     * base64字符串转化成图片
     */
    public static boolean GenerateImage(String imgStr) { // 对字节数组字符串进行Base64解码并生成图片
        /** 图像数据为空*/
        if (imgStr == null) {
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                // 调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
// 生成jpeg图片
            Date date = new Date();
            int random = (int) ((Math.random() * 10 - 1) + 10);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-hh");
            String format = simpleDateFormat.format(date);
            // 新生成的图片
            String imgFilePath = "F:/" + format + random + ".png";
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
