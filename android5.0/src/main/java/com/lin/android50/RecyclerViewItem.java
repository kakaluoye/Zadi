package com.lin.android50;

/**
 * Created by Administrator on 2016/7/16.
 */
public class RecyclerViewItem {
    private int resID;      //这里定义资源ID，模拟的图片ID。message模拟文字信息。
    private String message;

    public RecyclerViewItem() {
    }

    public int getResID() {
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RecyclerViewItem(int resID, String message) {

        this.resID = resID;
        this.message = message;
    }
}
