package com.example.gjj.myapplication.http.service;

import com.example.gjj.myapplication.model.MoneyModel;
import com.example.gjj.myapplication.model.Translation;
import com.example.gjj.myapplication.model.UserModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by 8 on 2018/5/2.
 */

public interface HttpService {
//    @Headers({
//            "userId: ",
//            "mac: 00000000"
//    })
    @GET("user/money")
    Call<MoneyModel> getUserInfo();

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Call<Translation> getCall();


    @FormUrlEncoded
    @POST("Juser/userLogin")
    Call<UserModel> getLogin(@Field("phone") long phone,
                             @Field("mac") String mac,
                             @Field("userPwd") String userPwd);
}
