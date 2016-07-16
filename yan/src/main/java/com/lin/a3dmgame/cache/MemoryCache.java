package com.lin.a3dmgame.cache;


import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by my on 2016/7/9.
 */
public class MemoryCache {
    //lruCache是键值对的缓存，这存放图片的路径和Bitmap对象,加上泛型。

    private LruCache<String, Bitmap> lruCache;

    public MemoryCache() {
        //初始化这里定义内存缓存的lrucache的大小。
        int size = (int) Runtime.getRuntime().freeMemory();
        //↑由Runtime对象得到空闲的内存
        lruCache = new LruCache<String, Bitmap>(size / 8) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes();//……………………………………
            }
        };
    }

    /**
     * 添加数据
     */
    public synchronized void addToMemory(String url, Bitmap bitmap) {
        if (url != null && bitmap != null) {
            lruCache.put(url, bitmap);
        }
    }

    /**
     * 从缓存中获取数据
     */

    public Bitmap getFromMemory(String url) {
        if (url != null) {
            return lruCache.get(url);
        }
        return null;
    }
}
