package com.lin.android50;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/7/16.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyRecyclerViewHolder> implements View.OnClickListener {

    private OnRecyclerViewItemClickListener listener;
    Context context;
    List<RecyclerViewItem> data;

    @Override
    public void onClick(View v) {
        if (listener != null) {// 如果设置了回调，则设置点击事件。即外面调用了listener,就设置点击监听。
            listener.onItemClick(v, (int) v.getTag());
        }
    }

    interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);//这里是真正的监听方法，适配器只是关联。在外面调用监听的时候重写这方法。
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
        Log.i("aaa","setOnItemClickListener");
    }

    public MyRecyclerViewAdapter(Context context, List<RecyclerViewItem> data) {
        this.context = context;
        this.data = data;
        Log.i("aaa","Adapter");
    }

    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_recyclerview_item, null);
        view.setOnClickListener(this);//对每一项添加监听，但监听没有，自定义接口自己添加。
        MyRecyclerViewHolder holder = new MyRecyclerViewHolder(view);
        //初始化ViewHolder，布局由布局填充器填充。得到的每一个View都添加监听。
        Log.i("aaa","onCreateViewHolder");
        return holder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewHolder holder, int position) {
        //绑定Holder的时候，给了位置，根据位置得到数据中的选择的对象。
        Log.i("aaa","onBindViewHolder1");
        RecyclerViewItem recyclerViewItem = data.get(position);
        //选择的对象里有它自己的数据，可以得到，绑定Holder就是给传来的Holder对象的每一项属性赋值。
        holder.textView.setText(recyclerViewItem.getMessage());
        Log.i("aaa","onBindViewHolder2");
        holder.imageview.setImageResource(recyclerViewItem.getResID());

        holder.itemView.setTag(position);
        //holder.itemView得到View对象。每个点击的View对象里都有含有自己位置的包裹。
        Log.i("aaa","onBindViewHolder3");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyRecyclerViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageview;
        private TextView textView;

        public MyRecyclerViewHolder(View itemView) {
            super(itemView);
            imageview = (ImageView) itemView.findViewById(R.id.recyclerview_item_iv);
            textView = (TextView) itemView.findViewById(R.id.recyclerview_item_tv);
            //Holder类需要传递个View对象才行。且view对象里有对应ID。
        }
    }
}
