package com.lin.android50.ok;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lin.android50.R;

public class CoordinatorLayoutActivity extends AppCompatActivity {

    FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);
        button = (FloatingActionButton) findViewById(R.id.coordinator_layout_fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(v, "Snackbar", Snackbar.LENGTH_LONG).show();
                Snackbar snackbar = Snackbar.make(v, "这是SnackBar哦", Snackbar.LENGTH_LONG);
                snackbar.setActionTextColor(Color.parseColor("#555555"));
                //SnackBar本身就是Bar，可以点击的，就是可以设置监听。
                View view = snackbar.getView();
                view.setBackgroundColor(Color.GREEN);
                //给SnackBar添加监听，就是给Bar添加动作。
                snackbar.setAction("SnackerBar的动作", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "你点击了哦", Toast.LENGTH_SHORT).show();
                    }
                });
                snackbar.show();
            }
        });
    }
}
