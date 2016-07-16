package com.game3d.my.listen;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.game3d.my.game3duse.GameActivity;
import com.game3d.my.game3duse.WenzhangBodyActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by my on 2016/7/8.
 */
public class GridItemSelectListener implements AdapterView.OnItemClickListener {
    Activity activity;
    List<HashMap<String,String>> data;

    public GridItemSelectListener(Activity activity, List<HashMap<String, String>> data) {
        this.activity = activity;
        this.data = data;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String,String> map = data.get(position);
        String litpic = map.get("litpic");
        String title = map.get("title");
        String typename = map.get("typename");
        String writer = map.get("writer");
        String keywords = map.get("keywords");
        String description = map.get("description");


        // Log.i("12345",arcurl+"--------------------");
        //进行跳转
        Intent intent = new Intent(activity, GameActivity.class);
        //intent.putExtra("url",arcurl);
        intent.putExtra("litpic",litpic);
        intent.putExtra("title",title);
        intent.putExtra("typename",typename);
        intent.putExtra("writer",writer);
        intent.putExtra("keywords",keywords);
        intent.putExtra("description",description);
        activity.startActivity(intent);
    }
}