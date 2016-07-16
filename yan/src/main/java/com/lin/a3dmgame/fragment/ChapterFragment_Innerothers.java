package com.lin.a3dmgame.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by my on 2016/7/10.
 */
@SuppressLint("ValidFragment")
public class ChapterFragment_Innerothers extends Fragment {

    private int type;

    @SuppressLint("ValidFragment")
    public ChapterFragment_Innerothers(int type) {
        this.type = type;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
