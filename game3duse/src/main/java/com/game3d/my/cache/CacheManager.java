package com.game3d.my.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import com.game3d.my.utils.BitmapTobyte;
import com.game3d.my.utils.CampressPic;

/**
 * Created by my on 2016/7/5.
 */
public class CacheManager {
    WebCache webCache = new WebCache();
    MemoryCache memoryCache = new MemoryCache();
    FileCache fileCache = new FileCache();
    Handler handler = new Handler();
    public void getBitmap(final ImageView iv,final String url){
        try {
        if(memoryCache.getFromMemory(url)!=null){
            iv.setImageBitmap(memoryCache.getFromMemory(url));
        }else{
                if(fileCache.getFromFile(url)!=null){
                    Bitmap bitmap = fileCache.getFromFile(url);
                    iv.setImageBitmap(bitmap);
                    memoryCache.insertToMemory(url,bitmap);

                }else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                byte[] bs = webCache.getFromNet(url);
                                final Bitmap bitmap = BitmapFactory.decodeByteArray(bs,0,bs.length);
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        byte[] data = BitmapTobyte.bitmapToByte(bitmap);
                                        Bitmap afterCam = CampressPic.compress(data,60,60);
                                        iv.setImageBitmap(afterCam);
                                        try {
                                            fileCache.saveToFile(data,url);
                                            memoryCache.insertToMemory(url,afterCam);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
