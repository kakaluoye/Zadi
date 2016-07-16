package com.game3d.my.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.game3d.my.cache.FileCache;
import com.game3d.my.cache.MemoryCache;
import com.game3d.my.game3duse.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by my on 2016/7/7.
 */
public class GameItemAdapter extends BaseAdapter {
    List<HashMap<String, String>> data;
    Context context;
    MemoryCache memoryCache;
    FileCache fileCache;
    ImageView imageView;
    Handler handler;

    public GameItemAdapter(Context context, List<HashMap<String, String>> data, Handler handler) {
        this.context = context;
        this.data = data;
        this.handler = handler;
        memoryCache = new MemoryCache();
        fileCache = new FileCache();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.game_item_gridview, null);
            holder.iv = (ImageView) convertView.findViewById(R.id.game_grid_item_imagview);
            holder.tv_title = (TextView) convertView.findViewById(R.id.game_gridview_item_tv_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HashMap<String, String> map = data.get(position);
        String title = map.get("title");
        final String litpic = map.get("litpic");
        Log.i("aaa",litpic+"-------------aaaaaaaaaaaaaaaaa");
        holder.tv_title.setText(title);
        //获取图片
        imageView = holder.iv;
        imageView.setImageResource(R.drawable.nopictrue_default);
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (memoryCache.getFromMemory(litpic) != null) {
                    final Bitmap bitmap = memoryCache.getFromMemory(litpic);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                                imageView.setImageBitmap(bitmap);
                            }
                    });
                } else {
                    try {
                        final Bitmap fromfile = fileCache.getFromFile(litpic);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                    imageView.setImageBitmap(fromfile);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        return convertView;
    }

    class ViewHolder {
        ImageView iv;
        TextView tv_title;

    }
}
