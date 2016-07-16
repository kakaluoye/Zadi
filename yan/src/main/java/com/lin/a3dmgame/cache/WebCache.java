package com.lin.a3dmgame.cache;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by my on 2016/7/9.
 */
public class WebCache {
    //网络缓存实际上就是由链接地址，读取得到byte[]数组。相当于HttpUtils。

    public byte[] getInfoFromNet(String urlPath) {
        ByteArrayOutputStream baos;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");//这里网页被表明是get请求。
            connection.setDoInput(true);//………………
            connection.setConnectTimeout(20*000);
            connection.setReadTimeout(20*000);
            connection.connect();
            if (connection.getResponseCode() == 200) {
                baos = new ByteArrayOutputStream();
                InputStream inputStream = connection.getInputStream();
                byte[] data = new byte[1024*5];
                int len = 0;
                while ((len = inputStream.read(data)) != -1) {
                    baos.write(data, 0, len);
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                return baos.toByteArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
