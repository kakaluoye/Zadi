package com.game3d.my.costumview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by my on 2016/7/6.
 */
public class WenzhangFirstFragmentViewPager extends ViewPager{
    public WenzhangFirstFragmentViewPager(Context context) {
        super(context);
    }

    public WenzhangFirstFragmentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}
