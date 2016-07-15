package com.lin.android50.ok;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lin.android50.R;

public class TabLayoutActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    String[] titles = new String[10];
    String[] contents = new String[10];
    MyPagerAdapter viewpageradapter;

    /**
     * 注意TabLayout一定要一致，都是支持包中的！！！！！
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        tabLayout = (TabLayout) findViewById(R.id.tablayout_activity_tl);
        viewPager = (ViewPager) findViewById(R.id.tablayout_activity_vp);
        initdata();
        //TabLayout里面的tab添加数据。
        for (int i = 0; i < 10; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
        }
        viewpageradapter = new MyPagerAdapter(getSupportFragmentManager(),titles);
        viewPager.setAdapter(viewpageradapter);
        //viewpager和TabLayout建立关系。
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                tab.setIcon(R.drawable.ad);//尝试，重复点tab设置图片。
            }
        });


    }

    private void initdata() {
        for (int i = 0; i < titles.length; i++) {
            titles[i] = "titles" + i;
        }
        for (int j = 0; j < contents.length; j++) {
            contents[j] = "contents" + j;
        }
    }
}
