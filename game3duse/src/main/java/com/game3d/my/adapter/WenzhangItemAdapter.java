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
import java.util.logging.SimpleFormatter;

/**
 * Created by my on 2016/7/7.
 */
public class WenzhangItemAdapter extends BaseAdapter {
    List<HashMap<String, String>> data;
    Context context;
    MemoryCache memoryCache;
    FileCache fileCache;
    ImageView imageView;
    Handler handler;

    public WenzhangItemAdapter(Context context, List<HashMap<String, String>> data, Handler handler) {
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
            convertView = View.inflate(context, R.layout.wenzhanglist_item, null);
            holder.iv = (ImageView) convertView.findViewById(R.id.wenzhang_list_item_iv);
            holder.tv_click = (TextView) convertView.findViewById(R.id.wenzhang_list_item_tv_click);
            holder.tv_title = (TextView) convertView.findViewById(R.id.wenzhang_list_item_tv_title);
            holder.tv_senddate = (TextView) convertView.findViewById(R.id.wenzhang_list_item_tv_senddate);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HashMap<String, String> map = data.get(position);
        long senddate = Long.parseLong(map.get("senddate").trim());

        String title = map.get("title");
        String click = map.get("click");
        final String litpic = map.get("litpic");
        holder.tv_title.setText(title);
        holder.tv_click.setText(click);
        Date date = new Date(senddate);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        holder.tv_senddate.setText(format.format(date));

        //获取图片
        imageView = holder.iv;
        imageView.setTag(litpic);
        imageView.setImageResource(R.drawable.nopictrue_default);
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (memoryCache.getFromMemory(litpic) != null) {
                    final Bitmap bitmap = memoryCache.getFromMemory(litpic);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (litpic.equals(imageView.getTag().toString())) {
                                imageView.setImageBitmap(bitmap);
                            }
                        }
                    });
                } else {
                    try {
                        final Bitmap fromfile = fileCache.getFromFile(litpic);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (litpic.equals(imageView.getTag().toString())) {
                                    imageView.setImageBitmap(fromfile);
                                }
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
        TextView tv_click;
        TextView tv_senddate;
    }
}
