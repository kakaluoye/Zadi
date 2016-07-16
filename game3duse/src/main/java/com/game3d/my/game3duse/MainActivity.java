package com.game3d.my.game3duse;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.game3d.my.fragmentofmain.GameFragment;
import com.game3d.my.fragmentofmain.LuntanFragment;
import com.game3d.my.fragmentofmain.WenzhangFragment;

import java.util.HashMap;
import java.util.List;


public class MainActivity extends FragmentActivity implements View.OnClickListener{
    //UI相关
    LinearLayout ll_wenzhang,ll_luntan,ll_game;
    FrameLayout frameLayout;
    //fragment
    Fragment f_wenzhang,f_luntan,f_game;
    FragmentManager fm ;
    FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        setColor(0);
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        transaction.replace(R.id.main_framelayout,f_wenzhang);
        transaction.commit();
    }
    public void initView(){
        ll_wenzhang = (LinearLayout) findViewById(R.id.main_wenzhang);
        ll_luntan = (LinearLayout) findViewById(R.id.main_luntan);
        ll_game = (LinearLayout) findViewById(R.id.main_game);
        frameLayout = (FrameLayout) findViewById(R.id.main_framelayout);
        f_wenzhang = new WenzhangFragment();
        f_luntan = new LuntanFragment();
        f_game = new GameFragment();
    }
    public void initListener(){
        ll_wenzhang.setOnClickListener(this);
        ll_luntan.setOnClickListener(this);
        ll_game.setOnClickListener(this);

    }

    //LinearLayout的监听
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_wenzhang:
                transaction = fm.beginTransaction();
                transaction.replace(R.id.main_framelayout,f_wenzhang);
                transaction.commit();
                setColor(0);
                break;
            case R.id.main_luntan:
                transaction = fm.beginTransaction();
                transaction.replace(R.id.main_framelayout,f_luntan);
                transaction.commit();
                setColor(1);
                break;
            case R.id.main_game:
                transaction = fm.beginTransaction();
                transaction.replace(R.id.main_framelayout,f_game);
                transaction.commit();
                setColor(2);
                break;
        }
    }
    public void setColor(int postion){
        resetColor();
        switch (postion){
            case 0:
                ll_wenzhang.setBackgroundColor(Color.parseColor("#3300ff00"));
                break;
            case 1:
                ll_luntan.setBackgroundColor(Color.parseColor("#3300ff00"));
                break;
            case 2:
                ll_game.setBackgroundColor(Color.parseColor("#3300ff00"));
                break;
        }
    }
    public void resetColor(){
        ll_game.setBackgroundColor(Color.BLACK);
        ll_wenzhang.setBackgroundColor(Color.BLACK);
        ll_luntan.setBackgroundColor(Color.BLACK);
    }


    //list的跳转监听

}