package com.game3d.my.game3duse;

import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.game3d.my.cache.FileCache;
import com.game3d.my.cache.MemoryCache;

/**
 * Created by my on 2016/7/8.
 */
public class GameActivity extends AppCompatActivity {
    ImageView iv;
    TextView tv_title,tv_typename,tv_writer,tv_keywords,tv_description;
    MemoryCache memoryCache;
    FileCache fileCache;
    ActionBar actionBar;
    Handler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_item_body);
        memoryCache = new MemoryCache();
        fileCache = new FileCache();
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        handler = new Handler();

        iv = (ImageView) findViewById(R.id.game_item_iv);
        tv_description = (TextView) findViewById(R.id.game_item_tv_description);
        tv_keywords = (TextView) findViewById(R.id.game_item_tv_keywords);
        tv_title = (TextView) findViewById(R.id.game_item_tv_title);
        tv_typename = (TextView) findViewById(R.id.game_item_tv_typename);
        tv_writer = (TextView) findViewById(R.id.game_item_tv_writer);
        Intent intent = getIntent();
        final String litpic = intent.getStringExtra("litpic");
        String title = intent.getStringExtra("title");
        String typename = intent.getStringExtra("typename");
        String writer = intent.getStringExtra("writer");
        String keywords = intent.getStringExtra("keywords");
        String description = intent.getStringExtra("description");
        tv_keywords.setText(keywords);
        tv_writer.setText(writer);
        tv_description.setText(description);
        tv_typename.setText(typename);
        tv_title.setText(title);

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (memoryCache.getFromMemory(litpic) != null) {
                    final Bitmap bitmap = memoryCache.getFromMemory(litpic);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            iv.setImageBitmap(bitmap);
                        }
                    });
                } else {
                    try {
                        final Bitmap fromfile = fileCache.getFromFile(litpic);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                iv.setImageBitmap(fromfile);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
