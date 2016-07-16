package com.lin.a3dmgame.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by my on 2016/7/10.
 * 对ViewPager添加数据，写一个继承FragmentPagerAdapter的自定义类的适配器。
 */
public class ChapterViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;


    public ChapterViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
