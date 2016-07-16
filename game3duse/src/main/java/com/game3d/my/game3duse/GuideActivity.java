package com.game3d.my.game3duse;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    ViewPager viewPager;
    LinearLayout linearLayout;
    List<View> views ;
   // int current = 0;
    ImageView[] imageViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initViewPager();
        imageViews = new ImageView[views.size()];
        linearLayout = (LinearLayout) findViewById(R.id.guide_ll);
        for(int i = 0;i<views.size();i++){
            imageViews[i] = (ImageView) linearLayout.getChildAt(i);
            //imageViews[i].setEnabled(false);
            if(i==views.size()-1){
                Button btn = (Button) views.get(i).findViewById(R.id.guide_page03_btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sharedPreferences = getSharedPreferences("isFirstUse", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("isFirstUse",false);
                        editor.commit();
                        Intent intent = new Intent(GuideActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }
       // imageViews[0].setEnabled(true);
        setSelect(0);

    }
    public void initViewPager(){
        viewPager = (ViewPager) this.findViewById(R.id.guide_viewpager);
        views = new ArrayList<>();
        View view1 = LayoutInflater.from(this).inflate(R.layout.guide_page01,null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.guide_page02,null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.guide_page03,null);
        views.add(view1);
        views.add(view2);
        views.add(view3);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                ((ViewPager)container).removeView(views.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ((ViewPager)container).addView(views.get(position));
                return views.get(position);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
        });
        viewPager.addOnPageChangeListener(this);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
       // Log.i("12345",imageViews.length+"");
        setSelect(position);
    }
    public void setSelect(int position){
        reset();
        setPic(imageViews[position],true);
    }
    public void reset(){
        setPic(imageViews[0],false);
        setPic(imageViews[1],false);
        setPic(imageViews[2],false);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setPic(ImageView iv,boolean flag){
        if(flag){
            iv.setImageResource(R.drawable.dot_white);
        }else{
            iv.setImageResource(R.drawable.dot_dark);
        }
    }
}
