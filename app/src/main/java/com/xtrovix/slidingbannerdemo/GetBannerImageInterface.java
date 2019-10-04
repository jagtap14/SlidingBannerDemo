package com.xtrovix.slidingbannerdemo;

import com.xtrovix.slidingbannerdemo.model.BannerImageModel;
import com.xtrovix.slidingbannerdemo.model.HomeImageModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface GetBannerImageInterface {
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("api/get_category_Type?")
    Call<BannerImageModel> get_type_categories(@Header("Authorization") String token);
}
