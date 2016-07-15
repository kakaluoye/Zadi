package com.lin.android50.ok;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Administrator on 2016/7/15.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    //这个适配器填充的是Fragment，通过传数据给Fragment，在Fragment里自己设置。
    String[] titles;

    public MyPagerAdapter(FragmentManager fm, String[] data) {
        super(fm);
        this.titles = data;
    }

    @Override
    public Fragment getItem(int position) {

        TabLayoutFragment fragment = new TabLayoutFragment();
        Bundle bundle = new Bundle();
        bundle.putString("CONTENT", titles[position]);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
