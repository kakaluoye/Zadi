package com.lin.a3dmgame.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lin.a3dmgame.R;
import com.lin.a3dmgame.cache.FileCache;
import com.lin.a3dmgame.cache.MemoryCache;

import java.util.HashMap;
import java.util.List;

/**
 * Created by my on 2016/7/10.
 */
public class ChapterFragmentInnerListViewAdapter extends BaseAdapter {
    //填充listview。并用handler显示ui。

    private List<HashMap<String, String>> data;
    private Context context;
    private Handler handler;
    private ImageView imageView;
    private MemoryCache memoryCache;
    private FileCache fileCache;

    public ChapterFragmentInnerListViewAdapter(Context context, List<HashMap<String, String>> data, Handler handler) {
        this.context = context;
        this.data = data;
        this.handler = handler;
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
        ViewHolder viewHolder =null;
        if (convertView==null){
            convertView = View.inflate(context, R.layout.chapterfragment_inner_item,null);
            viewHolder = new ViewHolder();
            viewHolder.iv= (ImageView) convertView.findViewById(R.id.chapter_inner_item_iv);
            viewHolder.tv_clicks = (TextView) convertView.findViewById(R.id.chapter_inner_item_tv_clicks);
            viewHolder.tv_time = (TextView) convertView.findViewById(R.id.chapter_inner_item_tv_time);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.chapter_inner_item_tv_title);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }

    class ViewHolder {
        ImageView iv;
        TextView tv_title;
        TextView tv_clicks;
        TextView tv_time;

    }
}
