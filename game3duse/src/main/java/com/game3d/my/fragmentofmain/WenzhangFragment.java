package com.game3d.my.fragmentofmain;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.game3d.my.adapter.WenzhangViewPagerAdapter;
import com.game3d.my.fragmentofmain.fragmentofwenzhang.WenzhangFirstFragment;
import com.game3d.my.fragmentofmain.fragmentofwenzhang.WenzhangOtherFragment;
import com.game3d.my.game3duse.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by my on 2016/7/6.
 */
public class WenzhangFragment extends Fragment implements RadioGroup.OnCheckedChangeListener,ViewPager.OnPageChangeListener{
    View view;
    HorizontalScrollView horizontalScrollView;
    RadioGroup radioGroup;
    RadioButton rb01_top,rb02_top,rb03_top,rb04_top,rb05_top,rb06_top,rb07_top,rb08_top,rb09_top,rb10_top;
    ViewPager viewPager;
    Fragment f1,f2,f3,f4,f5,f6,f7,f8,f9,f10;
    List<Fragment> list;
    WenzhangViewPagerAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      view  = View.inflate(getContext(), R.layout.fragment_wenzhang,null);
        initView();
        initListener();
        initFragment();
        adapter = new WenzhangViewPagerAdapter(getChildFragmentManager(),list);
        viewPager.setAdapter(adapter);
        setWhenClickRadio(0);
        return view;
    }
    public void initView(){
        horizontalScrollView = (HorizontalScrollView) view.findViewById(R.id.main_horizontalScrollView);
        radioGroup = (RadioGroup) view.findViewById(R.id.main_radiogroup);
        rb01_top = (RadioButton) view.findViewById(R.id.main_radiobutton_01);
        rb02_top = (RadioButton) view.findViewById(R.id.main_radiobutton_02);
        rb03_top = (RadioButton) view.findViewById(R.id.main_radiobutton_03);
        rb04_top = (RadioButton) view.findViewById(R.id.main_radiobutton_04);
        rb05_top = (RadioButton) view.findViewById(R.id.main_radiobutton_05);
        rb06_top = (RadioButton) view.findViewById(R.id.main_radiobutton_06);
        rb07_top = (RadioButton) view.findViewById(R.id.main_radiobutton_07);
        rb08_top = (RadioButton) view.findViewById(R.id.main_radiobutton_08);
        rb09_top = (RadioButton) view.findViewById(R.id.main_radiobutton_09);
        rb10_top = (RadioButton) view.findViewById(R.id.main_radiobutton_10);
        viewPager = (ViewPager) view.findViewById(R.id.main_viewpager);
    }
    public void initListener(){
        radioGroup.setOnCheckedChangeListener(this);
        viewPager.addOnPageChangeListener(this);
    }
    public void initFragment(){
        list = new ArrayList<>();
        f1 = new WenzhangFirstFragment(getActivity());
        f2 = new WenzhangOtherFragment(2);
        f3 = new WenzhangOtherFragment(151);
        f4 = new WenzhangOtherFragment(152);
        f5 = new WenzhangOtherFragment(153);
        f6 = new WenzhangOtherFragment(154);
        f7 = new WenzhangOtherFragment(196);
        f8 = new WenzhangOtherFragment(197);
        f9 = new WenzhangOtherFragment(199);
        f10 = new WenzhangOtherFragment(25);
        list.add(f1);
        list.add(f2);
        list.add(f3);
        list.add(f4);
        list.add(f5);
        list.add(f6);
        list.add(f7);
        list.add(f8);
        list.add(f9);
        list.add(f10);
    }
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.main_radiobutton_01:
                viewPager.setCurrentItem(0);
                break;
            case R.id.main_radiobutton_02:
                viewPager.setCurrentItem(1);
                break;
            case R.id.main_radiobutton_03:
                viewPager.setCurrentItem(2);
                break;
            case R.id.main_radiobutton_04:
                viewPager.setCurrentItem(3);
                break;
            case R.id.main_radiobutton_05:
                viewPager.setCurrentItem(4);
                break;
            case R.id.main_radiobutton_06:
                viewPager.setCurrentItem(5);
                break;
            case R.id.main_radiobutton_07:
                viewPager.setCurrentItem(6);
                break;
            case R.id.main_radiobutton_08:
                viewPager.setCurrentItem(7);
                break;
            case R.id.main_radiobutton_09:
                viewPager.setCurrentItem(8);
                break;
            case R.id.main_radiobutton_10:
                viewPager.setCurrentItem(9);
                break;
        }
    }
    public void setWhenClickRadio(int position){
        resetRadio();
        switch (position){
            case 0:
                rb01_top.setTextSize(20);
                rb01_top.setTextColor(Color.parseColor("#33ff00"));
                break;
            case 1:
                rb02_top.setTextSize(20);
                rb02_top.setTextColor(Color.parseColor("#33ff00"));
                break;
            case 2:
                rb03_top.setTextSize(20);
                rb03_top.setTextColor(Color.parseColor("#33ff00"));
                break;
            case 3:
                rb04_top.setTextSize(20);
                rb04_top.setTextColor(Color.parseColor("#33ff00"));
                break;
            case 4:
                rb05_top.setTextSize(20);
                rb05_top.setTextColor(Color.parseColor("#33ff00"));
                break;
            case 5:
                rb06_top.setTextSize(20);
                rb06_top.setTextColor(Color.parseColor("#33ff00"));
                break;
            case 6:
                rb07_top.setTextSize(20);
                rb07_top.setTextColor(Color.parseColor("#33ff00"));
                break;
            case 7:
                rb08_top.setTextSize(20);
                rb08_top.setTextColor(Color.parseColor("#33ff00"));
                break;
            case 8:
                rb09_top.setTextSize(20);
                rb09_top.setTextColor(Color.parseColor("#33ff00"));
                break;
            case 9:
                rb10_top.setTextSize(20);
                rb10_top.setTextColor(Color.parseColor("#33ff00"));
                break;

        }
    }
    public void resetRadio(){
        rb01_top.setTextSize(18);
        rb01_top.setTextColor(Color.BLACK);
        rb02_top.setTextSize(18);
        rb02_top.setTextColor(Color.BLACK);
        rb03_top.setTextSize(18);
        rb03_top.setTextColor(Color.BLACK);
        rb04_top.setTextSize(18);
        rb04_top.setTextColor(Color.BLACK);
        rb05_top.setTextSize(18);
        rb05_top.setTextColor(Color.BLACK);
        rb06_top.setTextSize(18);
        rb06_top.setTextColor(Color.BLACK);
        rb07_top.setTextSize(18);
        rb07_top.setTextColor(Color.BLACK);
        rb08_top.setTextSize(18);
        rb08_top.setTextColor(Color.BLACK);
        rb09_top.setTextSize(18);
        rb09_top.setTextColor(Color.BLACK);
        rb10_top.setTextSize(18);
        rb10_top.setTextColor(Color.BLACK);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        horizontalScrollView.setVisibility(View.VISIBLE);
        radioGroup.setVisibility(View.VISIBLE);
        int x = radioGroup.getChildAt(position).getLeft();
        horizontalScrollView.smoothScrollTo(x,0);
        setWhenClickRadio(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
