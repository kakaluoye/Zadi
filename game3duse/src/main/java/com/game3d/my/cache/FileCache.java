package com.game3d.my.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by my on 2016/7/5.
 */
public class FileCache {
    private static File root ;

    public FileCache() {
        if(isMounted()){
            root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        }
    }

    public static boolean isMounted(){
        boolean flag = false;
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            flag = true;
        }
        return flag;
    }
    public String getFileName(String urlpath){
        return urlpath.substring(urlpath.lastIndexOf("/")+1,urlpath.length());
    }

    /*
     * 将数据写入文件中
     */
    public synchronized String saveToFile(byte[] data,String urlpath) throws Exception{
        if(isMounted()){
            String filename = getFileName(urlpath);
            File file = new File(root,filename);
            if(file.exists()){
                file.delete();
            }
            String str = file.getAbsolutePath();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            if(fos!=null){
                fos.close();
            }
            return str;
        }
        return null;
    }
    /*
        从文件中读数据
     */
    public Bitmap getFromFile(String url) throws Exception {
        if(isMounted()) {
            String filename = getFileName(url);
            File file = new File(root, filename);
            FileInputStream fis = new FileInputStream(file);
            return BitmapFactory.decodeStream(fis);
        }
        return  null;
    }
}
