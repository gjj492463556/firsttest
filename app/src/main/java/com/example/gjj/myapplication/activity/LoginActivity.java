package com.example.gjj.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.gjj.myapplication.R;

/**
 * 测试可视化布局ConstraintLayout
 * Created by 8 on 2018/5/8.
 */

public class LoginActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
