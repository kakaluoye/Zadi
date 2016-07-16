package com.lin.a3dmgame.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.util.Log;


import com.lin.a3dmgame.cache.FileCache;
import com.lin.a3dmgame.cache.MemoryCache;
import com.lin.a3dmgame.cache.WebCache;
import com.lin.a3dmgame.commondao.NewsDao;
import com.lin.a3dmgame.models.News;
import com.lin.a3dmgame.utils.BitmapToByte;
import com.lin.a3dmgame.utils.CompressIcon;
import com.lin.a3dmgame.utils.JsonUtils;

import java.util.List;

public class DownLoadService extends Service {
    //需要请求的数据的网页很多，用一个数组来存放。
    String[] urlPaths = {"http://www.3dmgame.com/sitemap/api.php?row=10&typeid=181&paging=1&page=1",
            "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=182&paging=1&page=1",
            "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=183&paging=1&page=1",
            "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=184&paging=1&page=1",
            "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=2&paging=1&page=1",
            "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=151&paging=1&page=1",
            "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=152&paging=1&page=1",
            "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=153&paging=1&page=1",
            "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=154&paging=1&page=1",
            "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=196&paging=1&page=1",
            "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=197&paging=1&page=1",
            "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=199&paging=1&page=1",
            "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=25&paging=1&page=1"};
    MemoryCache memoryCache;
    FileCache fileCache;
    WebCache webCache;
    NewsDao dao;

    public DownLoadService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        memoryCache = new MemoryCache();
        fileCache = new FileCache();
        webCache = new WebCache();
        dao = new NewsDao(getApplicationContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("aaa", "service,线程开始下载数据");
        //service的onStartCommand方法里进行数据的加载等耗时操作。需要在子线程里。
        // 数据的加载用三级缓存，web,file,cache。且图片要压缩后再保存，不然东西太多太大。
        download(urlPaths[0]);
        download(urlPaths[1]);
        download(urlPaths[2]);
        download(urlPaths[3]);
        download(urlPaths[4]);
        download(urlPaths[5]);
        download(urlPaths[6]);
        download(urlPaths[7]);
        download(urlPaths[8]);
        download(urlPaths[9]);
        download(urlPaths[10]);
        download(urlPaths[11]);
        download(urlPaths[12]);
        Log.i("aaa", "service,下载线程都开启了");
        return START_REDELIVER_INTENT;//重复投递……………………………………
    }

    public void download(final String urlPath) {
        //图片压缩后保存到三级缓存，所有的信息加入数据库。
        //先别慌着加入数据库，加入数据库的图片地址换成缓存的sd卡的地址，
        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] by = webCache.getInfoFromNet(urlPath);
                if (by == null) {
                    Log.i("aaa", "service,网络第一次解析下载数据为空");
                    return;
                }
                try {
                    Log.i("aaa", "service,下载数据开始,当前线程：：："+Thread.currentThread().getName());
                    String Json = new String(by, "utf-8");
                    List<News> newsList = JsonUtils.getList(Json, 10);
//解析出来的图片地址，没有www网页前缀，在Json工具得到新闻的List对象的时候，给每个news对象的图片属性，添加上了www的前缀。解析好为下载做准备。不然没前缀的图片地址没法直接下载。
                    Log.i("aaa", newsList.size() + "service里解析的新闻个数###########");
                    for (News news : newsList) {//只得到图片。
                        String iconurl = news.getLitpic();//这一步网络下载news对象的图片数据地址已经加上了www的图片地址。
                        byte[] data = webCache.getInfoFromNet(iconurl);//下载图片。成byte数组。
                        if (data != null) {//如果下载的图片的byte数组不为空在进行图片的压缩，缓存。
                            Bitmap bitmap = CompressIcon.getCompressionImage(data, 80, 80);
                            if (bitmap != null) {//压缩之后的图片Bitmap对象。
                                byte[] afterdata = BitmapToByte.zhuanhuan(bitmap);//压缩之后是Bitmap对象，保存文件缓存需要byte[]类型。这步转换下。
                                String filename = fileCache.saveFile(afterdata, iconurl);//返回的是图片的绝对SD卡路径
                                //这里传的是byte数组，需要把压缩后的Bitmap转换成byte数组。
                                memoryCache.addToMemory(filename, bitmap);
                                //都加入缓存了，再加入数据库里，数据库加的是news对象。不只是图片
                                news.setLitpic(filename);//news对象的Litpic属性改成SD卡绝对路径，放在数据库，取的时候取的是图片的SD卡地址。
                                dao.addNewsToSQliteTable(news, filename);//加入的时候，图片的值改成SD卡的路径，在上面set
                                Log.i("aaa", "service,下完咯,当前线程：：："+Thread.currentThread().getName());
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
