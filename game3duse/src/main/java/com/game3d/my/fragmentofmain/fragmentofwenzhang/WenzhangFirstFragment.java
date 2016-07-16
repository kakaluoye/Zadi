package com.game3d.my.fragmentofmain.fragmentofwenzhang;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.game3d.my.adapter.WenzhangFirstFragmentViewpagerAdapter;
import com.game3d.my.adapter.WenzhangItemAdapter;
import com.game3d.my.costumview.WenzhangFirstFragmentViewPager;
import com.game3d.my.game3duse.R;
import com.game3d.my.listen.ListItemSelectListener;
import com.game3d.my.sqlitehelper.SqliteHander;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by my on 2016/7/6.
 */
@SuppressLint("ValidFragment")
public class WenzhangFirstFragment extends Fragment {
    Activity activity;

    @SuppressLint("ValidFragment")
    public WenzhangFirstFragment(Activity activity) {
        this.activity = activity;
    }

    WenzhangFirstFragmentViewPager viewPager;
    View view;
    int[] img = {R.drawable.default1, R.drawable.default2, R.drawable.default3};
    List<ImageView> list;
    ListView listView;
    WenzhangFirstFragmentViewpagerAdapter adapter;
    //记录当前位置
    int page = 0;
    PullToRefreshListView pullToRefreshListView;
    //取出数据库中的数据
    Cursor cursor;
    Handler handler;
    List<HashMap<String, String>> data;
    SqliteHander sqliteHander;
    WenzhangItemAdapter listadapter;
    ListItemSelectListener listItemSelectListener;
    //显示第几页
    int pageindex = 1;
    //显示一页几条数据
    int pagesize = 10;


    PullToFreshListener pullToFreshListener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.wenzhang_firstfragment, null);
        list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(img[i]);
            list.add(imageView);
        }
        viewPager = (WenzhangFirstFragmentViewPager) view.findViewById(R.id.wenzhang_first_viewpager);
        adapter = new WenzhangFirstFragmentViewpagerAdapter(list);
        viewPager.setAdapter(adapter);

        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.wenzhang_first_pulltorefreshlistview);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToFreshListener = new PullToFreshListener();
        pullToRefreshListView.setOnRefreshListener(pullToFreshListener);


        //对数据进行处理
        handler = new Handler();
        data = new ArrayList<>();
        sqliteHander = new SqliteHander(getContext());
        listadapter = new WenzhangItemAdapter(getContext(), data, handler);
        listView = pullToRefreshListView.getRefreshableView();
        listView.setAdapter(listadapter);
        setData();
        listItemSelectListener = new ListItemSelectListener(getActivity(),data);
        listView.setOnItemClickListener(listItemSelectListener);
        auto();
        return view;
    }

    //设置viewpager自动滚动
    public void auto() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (page > 1) {
                    page = 0;
                } else {
                    page++;
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        viewPager.setCurrentItem(page);

                    }
                });
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);
    }

    //得到数据
    public void setData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                cursor = sqliteHander.getCursorlimit(pageindex, pagesize);
                while (cursor.moveToNext()) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    String litpic = cursor.getString(cursor.getColumnIndex("litpic"));
                    String title = cursor.getString(cursor.getColumnIndex("title"));
                    String click = cursor.getString(cursor.getColumnIndex("click"));
                    String senddate = cursor.getString(cursor.getColumnIndex("senddate"));
                    String arcurl = cursor.getString(cursor.getColumnIndex("arcurl"));
                    Log.i("12345",arcurl+"+++++++");
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
                        listadapter.notifyDataSetChanged();
                        pullToRefreshListView.onRefreshComplete();
                    }
                });
            }
        }).start();
    }

    //pull的 监听
    class PullToFreshListener implements PullToRefreshBase.OnRefreshListener2 {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {
            if(pageindex>8){
                Toast.makeText(getContext(),"最后一页了",Toast.LENGTH_LONG);
            }else{
                data.clear();
                pageindex++;
                setData();
            }
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView) {
            if(pageindex>8){
                Toast.makeText(getContext(),"最后一页了",Toast.LENGTH_LONG);
            }else{
                pageindex++;
                setData();
            }
        }
    }


}
