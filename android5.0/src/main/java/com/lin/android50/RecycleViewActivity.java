package com.lin.android50;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.lin.android50.R.id.recyclerviewactivity_recycleview;

public class RecycleViewActivity extends AppCompatActivity {


    RecyclerView recyclerView;

    //    List<Recycleritem> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        recyclerView = (RecyclerView) findViewById(recyclerviewactivity_recycleview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RecycleViewActivity.this);
        //得到RecyclerView需要的LinearLayoutManager，设置控件类型放方芳芳
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);//
        //添加分割线。
        recyclerView.addItemDecoration(new RecyclerViewItemDevider());//需要传入一个ItemDecoration。新建子类。
        //数据
        initDate();

    }

    private void initDate() {

    }
}
