package com.lin.android50;

import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SwipeRefreshLayoutActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    List<RecyclerViewItem> data = new ArrayList<>();
    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout);
        initSwipeRefreshLayout();
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.swiperefreshlayout_rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        //得到RecyclerView需要的LinearLayoutManager，设置控件类型放方芳芳
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(linearLayoutManager);//设置一个管理者。可以是LinearLayoutManage，或者GridLayoutManege。

        //添加分割线。
        recyclerView.addItemDecoration(new MyItemDecoration());//需要传入一个ItemDecoration。新建子类。
        //数据
        initDate();
        adapter = new MyRecyclerViewAdapter(getApplicationContext(), data);
        //没有监听，需要自己定义，不能在recyclerview里定义吧，整好定义的自定义适配器，在自定义适配器里定义监听接口。
        recyclerView.setAdapter(adapter);
        Log.i("aaa", "setAdapter");
        //这里不同的是，因为没有监听，自己在adapter里写的接口监听，对每一项item添加监听要adapter调用自己内部的监听才行。
        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Snackbar.make(view, "你点击了第" + position + "个", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void initDate() {
        for (int i = 0; i < 20; i++) {
            Log.i("aaa", "initDate");
            RecyclerViewItem theObject = new RecyclerViewItem(R.drawable.ad, "message" + i);
            data.add(theObject);
        }
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout_srl);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.YELLOW);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLACK, Color.BLUE);
        swipeRefreshLayout.setProgressViewOffset(false, 0, 40);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<RecyclerViewItem> list = new ArrayList<RecyclerViewItem>();
                        for (int i = 0; i < 5; i++) {
                            list.add(new RecyclerViewItem(R.drawable.ad, "new data" + i+100));
                        }
                        list.addAll(data);
                        data.clear();
                        data.addAll(list);//为了让新加的数据在上面显示。
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 5000);
            }
        });
    }
}
