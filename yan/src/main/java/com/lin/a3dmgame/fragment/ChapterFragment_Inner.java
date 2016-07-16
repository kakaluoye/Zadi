package com.lin.a3dmgame.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;


import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lin.a3dmgame.R;
import com.lin.a3dmgame.adapter.ChapterFragmentInnerListViewAdapter;
import com.lin.a3dmgame.adapter.ChapterFragmentInnerViewPagerAdapter;
import com.lin.a3dmgame.commondao.NewsDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by my on 2016/7/10.
 */
@SuppressLint("ValidFragment")
public class ChapterFragment_Inner extends Fragment {

    private FrameLayout frameLayout;
    private ViewPager viewPager;
    private PullToRefreshListView pullToRefreshListView;
    private View view;

    private ChapterFragmentInnerViewPagerAdapter innerViewPagerAdapter;
    int[] icons = {R.drawable.innerfragment_default1, R.drawable.innerfragment_default2, R.drawable.innerfragment_default3};
    private List<ImageView> listimgs;
    private Activity activity;

    @SuppressLint("ValidFragment")
    public ChapterFragment_Inner(Activity activity) {
        this.activity = activity;
    }

    private List<HashMap<String, String>> data;
    private NewsDao dao;

    private Handler handler;
    private int page = 0;//当前的页数。
    private int pageindex = 1;//显示第几页。
    private int pagesize = 10;//显示的每页数据。

    private ChapterFragmentInnerListViewAdapter listViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_main_chapterfragment_inner, null);

        viewPager = (ViewPager) view.findViewById(R.id.main_chapterfragment_inner_vp);
        listimgs = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            //for循环就是为了把数据，也就是3图片放到集合里，在放到适配器中。
            //new了一个图片对象，图片对象设置自己的图片资源id,再假如list。
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(icons[i]);
//！！其实也可以不要上面和for循环，直接把3图片添加到list集合，可这样设置图片充满它所属于的布局，且图片多时，这样很方便！！！
            listimgs.add(imageView);
        }
        innerViewPagerAdapter = new ChapterFragmentInnerViewPagerAdapter(listimgs);
        //viewPager用PagerAdapter的子类适配器来填充它。自定义了个填充类。填充器都需要数据，上面3图片就是，穿进去就ok。
        viewPager.setAdapter(innerViewPagerAdapter);
        //给viewPager↑、pullToRefreshListview↓都添加数据。都是是用适配器添加数据。
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.main_chapterfregment_inner_pulltolistview);
        //需要listview的适配器，去创建吧，和布局。适配器需要数据，先从数据库里取出来。
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);//设置拉刷新的listview的模式为上下拉都刷新。
//        pullToRefreshListView.set
        //^………………………………设置刷新完毕消失，和没数据的时候显示默认的没网络为加载说明 。
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });

        handler = new Handler();//os包下的handler………………………………
        data = new ArrayList<HashMap<String, String>>();
        dao = new NewsDao(getContext());
        new Thread(new Runnable() {
            @Override
            public void run() {
                Cursor cursor = dao.getCursorPaging(pageindex, pagesize);
                while (cursor.moveToNext()) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    String litpic = cursor.getColumnName(cursor.getColumnIndex("litpic"));
                    String title = cursor.getColumnName(cursor.getColumnIndex("title"));
                    String clicks = cursor.getColumnName(cursor.getColumnIndex("click"));
                    String senddate = cursor.getColumnName(cursor.getColumnIndex("senddate"));
                    String arcurl = cursor.getColumnName(cursor.getColumnIndex("arcurl"));
                    map.put("litpic", litpic);
                    map.put("clicks", clicks);
                    map.put("senddate", senddate);
                    map.put("arcurl", arcurl);
                    data.add(map);
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listViewAdapter.notifyDataSetChanged();
                        pullToRefreshListView.onRefreshComplete();
                    }
                });
            }
        }).start();//添加数据在data里面。
        listViewAdapter = new ChapterFragmentInnerListViewAdapter(getContext(), data, handler);
        ListView listView = pullToRefreshListView.getRefreshableView();
        listView.setAdapter(listViewAdapter);
        //设置数据。
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return view;
    }
}
