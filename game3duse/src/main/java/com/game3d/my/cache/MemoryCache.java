package com.game3d.my.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by my on 2016/7/5.
 */
public class MemoryCache {
    private LruCache<String,Bitmap> lruCache;

    public MemoryCache() {
        int size = (int) Runtime.getRuntime().freeMemory();
        lruCache = new LruCache<String,Bitmap>(size/8){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight()/1024;
            }
        };
    }
    /*
        向缓存中增加数据
     */
    public synchronized void insertToMemory(String url,Bitmap bitmap){
        if(url!=null){
            if(bitmap!=null){
                lruCache.put(url,bitmap);
            }
        }
    }
    /*
        从缓存中获取数据
     */
    public Bitmap getFromMemory(String url){
        if(url!=null){
            if(lruCache.get(url)!=null){
                return lruCache.get(url);
            }
        }
        return null;
    }
}
