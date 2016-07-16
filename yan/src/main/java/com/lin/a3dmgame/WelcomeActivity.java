package com.lin.a3dmgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;


import com.lin.a3dmgame.service.DownLoadService;
import com.lin.a3dmgame.utils.NetUtils;

import pl.droidsonroids.gif.GifImageView;

public class WelcomeActivity extends AppCompatActivity {

    private GifImageView gifImageView;
    private Animation animation;
    boolean netOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        gifImageView = (GifImageView) findViewById(R.id.welcome_gifiv);
        animation = new AlphaAnimation(0, 1.0f);
        animation.setDuration(5000);
        gifImageView.startAnimation(animation);
        Log.i("aaa","start");
        //animation，动画对象，只是一个动画的模式。gif图片本身开启一个这样的动画模式。下面是动画模式监听。
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                netOpen = NetUtils.netConnect(WelcomeActivity.this);
                Log.i("aaa","网络连接没？=="+netOpen);
                if (netOpen) {//动画的时候，就开启service后台下载了。
                    Log.i("aaa","网络连接上了，开启服务");
                    Intent intent = new Intent(WelcomeActivity.this, DownLoadService.class);
                    startService(intent);
                    /**
                     * 我说怎么前面后面都执行了，就是启动不了service，晕，service是四大组件，必须在清单文件中配置。
                     * 整个工程是文件新弄过来的，清单文件中加了权限，加了Activity就是没加service，找了半天,Log了半天才找到。呵呵
                     */
                    Log.i("aaa","网络连接上了，已经开启服务");
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i("aaa","onAnimationEnd动画执行完毕");
                if (!netOpen) {
                    Toast.makeText(WelcomeActivity.this, "请连接您的网路", Toast.LENGTH_SHORT).show();
                }
                isFirstLogin();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public void isFirstLogin() {
        Log.i("aaa","isFirstLogin判断是非第一次登陆");
        SharedPreferences sharedPreferences = getSharedPreferences("isFirstLogin", Context.MODE_PRIVATE);
        boolean isLogin = sharedPreferences.getBoolean("isLogin", false);
        Log.i("aaa","是吗？=="+isLogin);
        if (!isLogin) {
            Log.i("aaa","跳转欢迎");
            Intent intent = new Intent(WelcomeActivity.this, GuideActivity.class);
            startActivity(intent);
            finish();
        } else {
            Log.i("aaa","跳转main");
            Intent intent1 = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent1);
            finish();
        }
    }
}
