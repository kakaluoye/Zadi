package com.lin.a3dmgame.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 为什么创建这个这个自定义Viewpager呢，可以不让外面大的布局滑动点击给影响本身的滑动。
 * Created by my on 2016/7/6.
 */
public class MainChapterFragmentViewpager extends ViewPager {

    public MainChapterFragmentViewpager(Context context) {
        super(context);
    }

    public MainChapterFragmentViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //这里不允许父类拦截触摸事件
        //getParent()得到父类的View对象，再请求不允许打断（就是有别的监听在，这个view不能行事这个监听）触摸事件为真。
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}
