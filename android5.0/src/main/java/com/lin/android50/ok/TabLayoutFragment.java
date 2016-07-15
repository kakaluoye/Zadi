package com.lin.android50.ok;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lin.android50.R;

/**
 * Created by Administrator on 2016/7/15.
 */
public class TabLayoutFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tablayout_item, container, false);
        Bundle bundle = getArguments();
        String title = bundle.getString("CONTENT");
        TextView textView = (TextView) view.findViewById(R.id.tablayout_activity_item_tv);
        textView.setText(title);
        return view;
    }
}
