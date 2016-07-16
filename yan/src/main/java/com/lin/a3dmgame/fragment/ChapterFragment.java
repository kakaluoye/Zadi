package com.lin.a3dmgame.fragment;

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

import com.lin.a3dmgame.R;
import com.lin.a3dmgame.adapter.ChapterFragmentInnerViewPagerAdapter;
import com.lin.a3dmgame.adapter.ChapterViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by my on 2016/7/11.
 */
public class ChapterFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private int typeid;
    private ChapterFragmentInnerViewPagerAdapter pagerAdapter;

    private View view;

    private HorizontalScrollView hscrollv;
    private RadioGroup rg;
    private RadioButton rb01, rb02, rb03, rb04, rb05, rb06, rb07, rb08, rb09, rb10;
    private ViewPager viewPager;
    private Fragment f1, f2, f3, f4, f5, f6, f7, f8, f9, f10;
    private List<Fragment> fragmentList;
    private ChapterViewPagerAdapter fragmentPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.activity_main_chapterfragment, null);
        //在文章碎片里，把数据得到，显示出来。谁替换加载这碎片，就会初始化这碎片，这碎片就会显示布局和内容。
        initView();//在别的方法里，需要把碎片view提到外面。
        initListener();
        initFragments();
        fragmentPagerAdapter = new ChapterViewPagerAdapter(getChildFragmentManager(), fragmentList);
        viewPager.setAdapter(fragmentPagerAdapter);


        return view;
    }

    private void initFragments() {
        fragmentList = new ArrayList<>();
        f1 = new ChapterFragment_Inner(getActivity());
        f2 = new ChapterFragment_Innerothers(2);
        f3 = new ChapterFragment_Innerothers(151);
        f4 = new ChapterFragment_Innerothers(152);
        f5 = new ChapterFragment_Innerothers(153);
        f6 = new ChapterFragment_Innerothers(154);
        f7 = new ChapterFragment_Innerothers(196);
        f8 = new ChapterFragment_Innerothers(197);
        f9 = new ChapterFragment_Innerothers(199);
        f10 = new ChapterFragment_Innerothers(25);
        fragmentList.add(f1);
        fragmentList.add(f2);
        fragmentList.add(f3);
        fragmentList.add(f4);
        fragmentList.add(f5);
        fragmentList.add(f6);
        fragmentList.add(f7);
        fragmentList.add(f8);
        fragmentList.add(f9);
        fragmentList.add(f10);
    }

    private void initListener() {
        rg.setOnCheckedChangeListener(this);//对RadioGroup添加监听，而不是RadioButton.在监听里实现没个按钮对应的相应操作。
        viewPager.addOnPageChangeListener(this);//对ViewPager来说，要添加add监听，set已经过时了。
    }

    private void initView() {
        hscrollv = (HorizontalScrollView) view.findViewById(R.id.main_chapterfragment_hsv);
        rg = (RadioGroup) view.findViewById(R.id.main_chapterfragment_rg);
        rb01 = (RadioButton) view.findViewById(R.id.main_chapterfragment_rb01);
        rb02 = (RadioButton) view.findViewById(R.id.main_chapterfragment_rb02);
        rb03 = (RadioButton) view.findViewById(R.id.main_chapterfragment_rb03);
        rb04 = (RadioButton) view.findViewById(R.id.main_chapterfragment_rb04);
        rb05 = (RadioButton) view.findViewById(R.id.main_chapterfragment_rb05);
        rb06 = (RadioButton) view.findViewById(R.id.main_chapterfragment_rb06);
        rb07 = (RadioButton) view.findViewById(R.id.main_chapterfragment_rb07);
        rb08 = (RadioButton) view.findViewById(R.id.main_chapterfragment_rb08);
        rb09 = (RadioButton) view.findViewById(R.id.main_chapterfragment_rb09);
        rb10 = (RadioButton) view.findViewById(R.id.main_chapterfragment_rb10);
        viewPager = (ViewPager) view.findViewById(R.id.main_chapterfragment_viewpager);
        rb01.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.main_chapterfragment_rb01:

                break;
            case R.id.main_chapterfragment_rb02:
                break;

        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
