package com.game3d.my.listen;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.game3d.my.game3duse.WenzhangBodyActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by my on 2016/7/8.
 */
public class ListItemSelectListener implements AdapterView.OnItemClickListener {
    Activity activity;
    List<HashMap<String,String>> data;

    public ListItemSelectListener(Activity activity, List<HashMap<String, String>> data) {
        this.activity = activity;
        this.data = data;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String,String> map = data.get(position);
        String arcurl =map.get("arcurl");
        //进行跳转
        Intent intent = new Intent(activity, WenzhangBodyActivity.class);
        intent.putExtra("url",arcurl);
        activity.startActivity(intent);
    }
}