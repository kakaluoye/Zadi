package com.lin.android50.ok;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lin.android50.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextInputlayoutActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout textInputLayout;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_inputlayout);
        btn = (Button) findViewById(R.id.ok_textinputlayout_btn);
        textInputLayout = (TextInputLayout) findViewById(R.id.ok_textinputlayout_til);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText editText = (EditText) textInputLayout.findViewById(R.id.ok_textinputlayout_et);
        String etText = editText.getText().toString().trim();
        boolean isok = validateContext(etText);//这两步要学会把方法提出来。这样思路就会很清晰。
        returnResult(isok);
    }

    private boolean validateContext(String inputText) {
        String pattenText = "^[A-Za_z0-9]+$";
        //把正则表达式转成java对象。！！！！！！！！！！
        Pattern pattern = Pattern.compile(pattenText);//把正则表达式解析成java对象。
        Matcher matcher = pattern.matcher(inputText);//进行比较得到matcher对象里面有匹配的boolean值。
        return matcher.matches();
    }

    private void returnResult(boolean isSuccess) {
        if (isSuccess) {
            textInputLayout.setErrorEnabled(false);//正确，就不需要提示。关闭提示功能。
        } else {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("输入错误");
        }
    }
}
