package com.example.gjj.myapplication.Ustils;

import android.app.Activity;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * Created by 8 on 2018/5/2.
 */

public class PermissionUtils {
    public static void permission(Activity activity, String[] string, final Repermission rePermission){
        RxPermissions rxPermission = new RxPermissions(activity);
        rxPermission
                .requestEach(string
//                        Manifest.permission.ACCESS_FINE_LOCATION,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.READ_CALENDAR,
//                        Manifest.permission.READ_CALL_LOG,
//                        Manifest.permission.READ_CONTACTS,
//                        Manifest.permission.READ_PHONE_STATE,
//                        Manifest.permission.READ_SMS,
//                        Manifest.permission.RECORD_AUDIO,
//                        Manifest.permission.CAMERA,
//                        Manifest.permission.CALL_PHONE,
//                        Manifest.permission.SEND_SMS
                )
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            // 用户已经同意该权限
                            rePermission.accept();
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            rePermission.refuse();
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                        } else {
                            rePermission.foreverRefuse();
                            // 用户拒绝了该权限，并且选中『不再询问』
                        }
                    }
                });
    }

    public interface Repermission{
        public void accept();
        public void refuse();
        public void foreverRefuse();
    }
}
