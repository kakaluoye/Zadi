package com.lin.android50.ok;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lin.android50.R;

public class FloatingActionButtonActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_actionbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("aaa", "FloatingActionButton点击了，执行了");
            }
        });
    }

}
