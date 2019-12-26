package com.example.watchudrive;


import com.example.watchudrive.PostItemModel.Comments;
import com.example.watchudrive.PostItemModel.PostItem;
import com.example.watchudrive.PostItemModel.PostItemResponse;
import com.example.watchudrive.PostItemModel.Review;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService{

    @GET("post_item")
    Call<PostItemResponse> getPosts(@Query("page_number")long page);


    @PUT("feed_item/{id}")
    Call<PostItem> updatePost(@Path("id")String _id,@Body PostItem postItem);


    @PUT("feed_item/{id}")
    Call<Comments> postComment(@Path("id")String _id,@Field("comments")Comments comments);


    @POST("review")
    Call<Review> postReview(@Body Review review);
}