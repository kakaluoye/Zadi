package com.game3d.my.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.util.Log;

import com.game3d.my.cache.FileCache;
import com.game3d.my.cache.MemoryCache;
import com.game3d.my.cache.WebCache;
import com.game3d.my.modles.News;
import com.game3d.my.sqlitehelper.SqliteHander;
import com.game3d.my.utils.BitmapTobyte;
import com.game3d.my.utils.CampressPic;
import com.game3d.my.utils.JsonUtils;

import java.util.List;

public class DownloadService extends Service {
    String[] urls = {"http://www.3dmgame.com/sitemap/api.php?row=10&typeid=181&paging=1&page=1",
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
    WebCache webCache;
    FileCache fileCache;
    MemoryCache memoryCache;
    SqliteHander hander;

    public DownloadService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        webCache = new WebCache();
        fileCache = new FileCache();
        memoryCache = new MemoryCache();
        hander = new SqliteHander(getApplicationContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
            download(urls[0]);
            download(urls[1]);
            download(urls[2]);
            download(urls[3]);
            download(urls[4]);
            download(urls[5]);
            download(urls[6]);
            download(urls[7]);
            download(urls[8]);
            download(urls[9]);
            download(urls[10]);
            download(urls[11]);
            download(urls[12]);

        return START_REDELIVER_INTENT;
    }
    public void download(final String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    byte[] bs = webCache.getFromNet(url);
                    if (bs == null) {
                        Log.i("12345", "网络解析的第一步是空");
                        return;
                    }
                    String json = new String(bs, "utf-8");
                    List<News> newsList = JsonUtils.getList(json, 10);
                    Log.i("12345", "解析出的news的个数" + newsList.size() + "--------");
                    //下载图片
                    for (News news : newsList) {
                        String litpic = news.getLitpic();
                        //下载图片
                        byte[] data = webCache.getFromNet(litpic);
                        if (data != null) {
                            //压缩
                            Bitmap aftercampress = CampressPic.compress(data, 80, 80);
                            if (aftercampress != null) {
                                byte[] afterdata = BitmapTobyte.bitmapToByte(aftercampress);
                                //保存图片
                                String filename = fileCache.saveToFile(afterdata, litpic);
                                memoryCache.insertToMemory(filename, aftercampress);
                                //将数据加入数据库
                                news.setLitpic(filename);
                                hander.insertToNewstable(news, filename);
                                // Log.i("12345", "完成一个数据");
                            } else {
                                //  Log.i("12345", "压缩出来是空");
                            }
                        } else {
                            //  Log.i("12345", "图片下载为空");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
