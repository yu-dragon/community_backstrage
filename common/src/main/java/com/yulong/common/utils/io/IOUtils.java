package com.yulong.common.utils.io;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class IOUtils {

    public static String InputStreamToString(HttpServletRequest request){
        ServletInputStream inputStream;
        try {
            inputStream = request.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException("request.getInputStream fail: ", e);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[64 * 1024];
        int len;
        try {
            while ((len = inputStream.read(bytes)) != -1){
                byteArrayOutputStream.write(bytes, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException("read from inputStream fail: ", e);
        }
        return new String(byteArrayOutputStream.toByteArray());
    }

}
