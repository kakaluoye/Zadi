package com.game3d.my.fragmentofmain.fragmentofwenzhang;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.game3d.my.adapter.WenzhangItemAdapter;
import com.game3d.my.game3duse.R;
import com.game3d.my.listen.ListItemSelectListener;
import com.game3d.my.sqlitehelper.SqliteHander;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by my on 2016/7/6.
 */
@SuppressLint("ValidFragment")
public class WenzhangOtherFragment extends Fragment {
    int type;
    View view;
    PullToRefreshListView pullToRefreshListView;
    ListView listview;
    List<HashMap<String,String>> data;
    WenzhangItemAdapter adapter ;
    Handler handler;
    Cursor cursor;
    int pageindex = 1;
    int pagesize = 10;

    SqliteHander sqliteHander;

    ListItemSelectListener listener;
//    public WenzhangOtherFragm nent() {
//}

    @SuppressLint("ValidFragment")
    public WenzhangOtherFragment(int type) {
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.wenzhang_otherfragment,null);
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.wenzhang_other_pulltorefreshlistview);
        listview = pullToRefreshListView.getRefreshableView();
        data = new ArrayList<>();
        handler = new Handler();
        adapter = new WenzhangItemAdapter(getContext(),data,handler);
        listview.setAdapter(adapter);
        sqliteHander = new SqliteHander(getContext());

        setData();
        listener = new ListItemSelectListener(getActivity(),data);
        listview.setOnItemClickListener(listener);
        return view;
    }
    //得到数据
    public void setData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                cursor = sqliteHander.getTypeCursor(type,pageindex, pagesize);
                if(cursor!=null){
                while (cursor.moveToNext()) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    String litpic = cursor.getString(cursor.getColumnIndex("litpic"));
                    String title = cursor.getString(cursor.getColumnIndex("title"));
                    String click = cursor.getString(cursor.getColumnIndex("click"));
                    String senddate = cursor.getString(cursor.getColumnIndex("senddate"));
                    String arcurl = cursor.getString(cursor.getColumnIndex("arcurl"));
                    map.put("litpic", litpic);
                    map.put("title", title);
                    map.put("click", click);
                    map.put("senddate", senddate);
                    map.put("arcurl",arcurl);
                    data.add(map);
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }else{
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(),"目前没有这种数据",Toast.LENGTH_LONG).show();
                            Log.i("12345","没有这种数据");
                        }
                    });
                }

            }

        }).start();
    }
}
