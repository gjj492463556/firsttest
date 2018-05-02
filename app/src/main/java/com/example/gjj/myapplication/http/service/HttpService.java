package com.example.gjj.myapplication.http.service;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Created by 8 on 2018/5/2.
 */

public interface HttpService {
    @GET("user/money")
    Call<Response> getUserInfo();
}
