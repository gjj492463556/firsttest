package com.example.gjj.myapplication;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.gjj.myapplication.activity.EditTextActivity;
import com.example.gjj.myapplication.http.rxjava.RxJavaUtils7;
import com.example.gjj.myapplication.http.service.HttpService;
import com.example.gjj.myapplication.model.UserModel;
import com.example.gjj.myapplication.utils.PermissionUtils;
import com.flyco.animation.BaseAnimatorSet;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    public void setBasIn(BaseAnimatorSet bas_in) {
        this.mBasIn = bas_in;
    }
    public void setBasOut(BaseAnimatorSet bas_out) {
        this.mBasOut = bas_out;
    }

    String[] str_permission = {Manifest.permission.CAMERA,Manifest.permission.CALL_PHONE};

    private static final String TAG = "RxPermissionTest";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.tv_camer, R.id.tv_retrofit2, R.id.tv_edittext, R.id.tv_rxjava})
    void onClicks(View view){
        switch (view.getId()){
            //相机测试 ---权限设置
            case R.id.tv_camer:
                PermissionUtils.permission(MainActivity.this, str_permission, new PermissionUtils.Repermission() {
                    @Override
                    public void accept() {
                        Toast.makeText(MainActivity.this,"接受",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void refuse() {
                        Toast.makeText(MainActivity.this,"拒绝，可以询问",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void foreverRefuse() {
                        Toast.makeText(MainActivity.this,"拒绝，不能询问",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
                //网络请求测试 ---Retrofit2.0
            case R.id.tv_retrofit2:
                request();
                break;
            case R.id.tv_edittext:
                startActivity(new Intent(MainActivity.this, EditTextActivity.class));
                break;
            //RxJava学习
            case R.id.tv_rxjava:
                Log.d("GJJ",Thread.currentThread().getName());
//                new RxjavaUtils().rxjavaUtils();
//                    new RxJavaUtils6().rxjav6();
                    new RxJavaUtils7().rxjava7();
                break;
        }
    }


    /**
     * 网络请求测试
     */
    private void request(){
        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://gxtyg168.com/")
//                                .baseUrl("http://192.168.1.157:8080/")
//                                .baseUrl("http://fy.iciba.com/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

        HttpService requestService = retrofit.create(HttpService.class);

        Call<UserModel> call = requestService.getLogin(Long.parseLong("15715626981"),"000000000","111111");
//        Call<Translation> call = requestService.getCall();

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
               Log.d("GJJ","请求成功结果"+response.body().getData().getUserId());
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.d("GJJ","请求失败");
            }
        });
    }


}
