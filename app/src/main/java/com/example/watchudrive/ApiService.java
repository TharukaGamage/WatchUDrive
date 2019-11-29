package com.example.watchudrive;


import com.example.watchudrive.PostItemModel.PostItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService{

    @GET("post_item")
    Call<PostItemResponse> getPosts(@Query("page_number")long page);
}