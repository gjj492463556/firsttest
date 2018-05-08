package com.example.gjj.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gjj.myapplication.R;
import com.example.gjj.myapplication.utils.DecimalInputTextWatcher;

/**
 * Created by 8 on 2018/5/8.
 */

public class EditTextActivity extends Activity{
    EditText decimal_input_et2;
    TextView tv_input_2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittext);

        decimal_input_et2 = (EditText) findViewById(R.id.decimal_input_et2);
        tv_input_2 = (TextView) findViewById(R.id.tv_input_2);

        decimal_input_et2.addTextChangedListener(new DecimalInputTextWatcher(decimal_input_et2,
                "DECIMAL", 2, new DecimalInputTextWatcher.AfterText() {
            @Override
            public void after(String string) {
                tv_input_2.setText("不限制整数位数,限制小数位数"+(TextUtils.isEmpty(string)?"":Double.parseDouble(string)));
            }
        }));
    }
}
