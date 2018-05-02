package com.example.gjj.myapplication;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.gjj.myapplication.Ustils.PermissionUtils;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;

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




    @OnClick({R.id.tv_camer})
    void onClicks(View view){
        switch (view.getId()){
            //相机测试
            case R.id.tv_camer:
//                NormalDialogStyleTwo();
//                showCamera();
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
        }
    }




    private void NormalDialogStyleTwo() {
        final NormalDialog dialog = new NormalDialog(this);
        dialog.content("确定要打开相机")//
                .style(NormalDialog.STYLE_TWO)//
                .titleTextSize(23)//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {

                        dialog.dismiss();
                    }
                });

    }

}
