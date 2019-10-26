package com.example.watchudrive;


import com.example.watchudrive.PostItemModel.PostItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService{

    @GET("posts")
    Call<PostItemResponse> getPosts(@Query("page")long page);
}