package com.lin.android50;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2016/7/14.
 */
public class RecyclerViewItemDevider extends RecyclerView.ItemDecoration {
    //每一个项目的绘制。项目的绘制对象。这里被调用是在画下划线红色的时候。
    //
    Paint paint;

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        super.onDraw(c, parent, state);//看它似乎是在自己一直调用自己。
        int left = 0;
        int right = parent.getWidth();

        int itemcount = parent.getChildCount();
        //有画布Cancas就用画笔paint来操作。
        if (paint == null) {
            paint = new Paint();//画笔不存在就new 一个，且设置画的属性。即颜色等。
            paint.setColor(Color.GREEN);
        }
        if (itemcount > 0) {
            //这个RecyclerView有Child的时候,画布再划线。
            for (int i = 0; i < itemcount; i++) {
                View viewitem = parent.getChildAt(i);
                c.drawLine(left, viewitem.getBottom(), right, viewitem.getBottom(), paint);
            }
        }
    }

    //当我们绘制每一个Item的时候之后执行
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);//这里就没事了，父类没有循环调用。
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
        //这里也是父类里调用自身，循环了，注释掉。
        outRect.set(0, 0, 0, 0);
        //得到每一项的弥补、抵消。就是设置每一个item的偏移量。输出的矩形。
    }
}
