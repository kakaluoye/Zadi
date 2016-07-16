package com.lin.a3dmgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lin.a3dmgame.adapter.GuideViewPageAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager vp;
    private LayoutInflater layoutinflater;
    private List<View> views;
    private PagerAdapter adapter;
    private ImageView[] dots;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        Log.i("aaa", "GuideActivity里");
        initView();
        initDot();
    }

    private void initDot() {
        //初始化所有的点图片。
        LinearLayout ll = (LinearLayout) findViewById(R.id.guide_activity_linearlayout);
        dots = new ImageView[views.size()];
        for (int i = 0; i < views.size(); i++) {
            dots[i] = (ImageView) ll.getChildAt(i);
            dots[i].setEnabled(true);//设置了默认都显示白色
        }
        //初始化 currentIndex，当前是第一页，把默认的点白色改成黑色。
        currentIndex = 0;
        dots[currentIndex].setEnabled(false);//点对象设置点击状态为false，状态false就自动变为黑色了。
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.guide_activity_vp);
        views = new ArrayList<>();
        layoutinflater = LayoutInflater.from(getApplicationContext());
        View view1 = layoutinflater.inflate(R.layout.activity_guide_page01, null);
        View view2 = layoutinflater.inflate(R.layout.activity_guide_page02, null);
        View view3 = layoutinflater.inflate(R.layout.activity_guide_page03, null);
        views.add(view1);
        views.add(view2);
        views.add(view3);
        adapter = new GuideViewPageAdapter(views);
        vp.setAdapter(adapter);
        vp.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position < 0 || position >= views.size()) {
            return;
        }
        dots[position].setEnabled(false);
        dots[currentIndex].setEnabled(true);
        currentIndex = position;

        if (position == views.size() - 1) {
            Button btn = (Button) views.get(position).findViewById(R.id.guide_page_btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setGuide();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    private void setGuide() {
        //保存一个登陆过的记录，记录放在分享存储里面。
        SharedPreferences sharedPreferences = getSharedPreferences("isFirstLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLogin", true);
        editor.commit();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
