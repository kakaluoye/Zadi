package com.game3d.my.game3duse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

import com.game3d.my.service.DownloadService;

import pl.droidsonroids.gif.GifImageView;

public class WelcomeActivity extends AppCompatActivity {
    GifImageView gifImageView;
    Animation animation;

    boolean isConnect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        gifImageView = (GifImageView) findViewById(R.id.welcom_gif);
        animation = new AlphaAnimation(0,1.0f);
        animation.setDuration(5000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isConnect = isConnect();
//                Log.i("12345",isConnect+"---");
                if(isConnect){
                    Intent intent = new Intent(WelcomeActivity.this, DownloadService.class);
                    startService(intent);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                doAfterAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        gifImageView.setAnimation(animation);

    }
    public void doAfterAnimation(){
//        Log.i("12345",isConnect+"+++");
        if(isConnect){
            if(isFirstUse()){
                Intent intent = new Intent(this,GuideActivity.class);
                startActivity(intent);
                finish();
            }else{
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
            }

        }else{
            Toast.makeText(WelcomeActivity.this,"请链接网络",Toast.LENGTH_LONG).show();
        }
    }
    public boolean isConnect(){
        boolean flag = false;
        ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if(networkInfo!=null||networkInfo.isAvailable()){
            flag=true;
        }
        return flag;
    }
    public boolean isFirstUse(){
        SharedPreferences sharedPreferences = getSharedPreferences("isFirstUse",Context.MODE_PRIVATE);
        boolean  flag = sharedPreferences.getBoolean("isFirstUse",true);
        return flag;
    }
}
