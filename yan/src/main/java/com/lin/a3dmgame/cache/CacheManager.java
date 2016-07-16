package com.lin.a3dmgame.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import com.lin.a3dmgame.utils.BitmapToByte;
import com.lin.a3dmgame.utils.CompressIcon;


/**
 * Created by my on 2016/7/9.
 */
public class CacheManager {
    /**
     * 根据图片地址，从缓存中依次找。找到图片，图片是放到控件中的，就直接传来控件。
     * 需要handler对象，post方法中的线程就相当于在主线程中。可以直接操作UI。当然只是传来的UI。
     */
    private WebCache webCache = new WebCache();
    private FileCache fileCache = new FileCache();
    private MemoryCache memoryCache = new MemoryCache();
    private Handler handler = new Handler();//可以是信息传递，这里是post直接设置UI。

    public void getBitmapSet(final ImageView imageView, final String urlPath) {
        if (memoryCache.getFromMemory(urlPath) != null) {
            imageView.setImageBitmap(memoryCache.getFromMemory(urlPath));
        } else {
            try {
                if (fileCache.getFromFile(urlPath) != null) {
                    imageView.setImageBitmap(fileCache.getFromFile(urlPath));
                    memoryCache.addToMemory(urlPath, fileCache.getFromFile(urlPath));
                } else {
                    //再不行就只能从网络下载了，但下载是耗时操作必须新建一个线程。
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            byte[] data = webCache.getInfoFromNet(urlPath);
                            //这里byte数组不能final，每次都需要用到。因此需要领一个对象。
                            final Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                            handler.post(new Runnable() {//这里回来试试怎么不转换。
                                @Override
                                public void run() {
                                    //这里进行图片的压缩。压缩是压缩的byte数组得到的是BitMap对象。
                                    //需要转换下
                                    byte[] data2 = BitmapToByte.zhuanhuan(bitmap);
                                    Bitmap afterBitmap = CompressIcon.getCompressionImage(data2, 50, 50);
                                    imageView.setImageBitmap(afterBitmap);
                                    //记得把图片放进更近的缓存里
                                    memoryCache.addToMemory(urlPath, afterBitmap);
                                    fileCache.saveFile(data2, urlPath);
                                }
                            });
                        }
                    }).start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
