package com.lin.a3dmgame.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by my on 2016/7/9.
 */
public class FileCache {
    //这是文件缓存    类   。能把文件保存，也能把文件取出。都是对SD卡文件的操作。
    //整个类就是对SD卡的所有操作。【方法意义】

    public boolean isMounted() {
        //SD卡操作先要判断【SD卡是否挂载】
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    private static File root;//加上静态，开始就有的数据。加不加静态没关系吧。

    public FileCache() {
        //对SD卡操作，首先要得到的是：SD卡存储的【公共下载根路径】。相对于跟路径下再做文件的操作。所以先得到它。
        if (isMounted()) {
            root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        }
    }

    //对于图片的数据来说，需要得到最好的图片名字
    public String getFileName(String urlPath) {
        return urlPath.substring(urlPath.lastIndexOf("/") + 1);
    }

    /**
     * 数据写入文件里，返回文件的全部的路径。
     */
    public synchronized String saveFile(byte[] data, String urlPath) {
        //………………………………………………………………这里多写即便再
        if (isMounted()) {
            String name = getFileName(urlPath);//name是纯粹的图片文件名。
            File file = new File(root, name);
            //为了避免每次启动数据一直增加。数据在的话，就删除了再写入文件。
            if (file.exists()){
                file.delete();
            }
            String s = file.getAbsolutePath();//file里是得到的内存卡的绝对路径。也是返回的sd卡路径。
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(data);
                if (fos != null) {
                    fos.close();
                }
                return s;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 读取数据,读取图片的缓存。存在里面的是byte[],这里取出直接取出图片。
     */
    public Bitmap getFromFile(String urlPath) throws Exception {
        //读取的是文件，用文件流,先得到文件file
        if (isMounted()) {
            String name = getFileName(urlPath);
            File file = new File(root, name);
            FileInputStream fis = new FileInputStream(file);
            return BitmapFactory.decodeStream(fis);
        }
        return null;
    }
}
