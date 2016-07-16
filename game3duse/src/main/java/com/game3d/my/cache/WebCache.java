package com.game3d.my.cache;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by my on 2016/7/5.
 */
public class WebCache {

    public  byte[] getFromNet(String str) throws Exception{
        URL url = new URL(str);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setConnectTimeout(20*1000);
        connection.setReadTimeout(20*1000);
        connection.connect();
        if(connection.getResponseCode()==200){
            InputStream is = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = 0;
            byte[] bs = new byte[4*1024];
            while((len = is.read(bs))!=-1){
                baos.write(bs,0,len);
            }
            if(is!=null){
                is.close();
            }
            return baos.toByteArray();
        }
        return null;
    }
}
