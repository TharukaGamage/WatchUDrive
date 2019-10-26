package com.example.watchudrive;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.watchudrive.PostItemModel.PostItem;
import com.example.watchudrive.PostItemModel.PostItemResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDataSource extends PageKeyedDataSource<Long, PostItem> {

    public static int PAGE_SIZE = 6;
    public static long FIRST_PAGE = 1;
    public static String TAG = PostDataSource.class.getSimpleName();


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params,
                            @NonNull LoadInitialCallback<Long, PostItem> callback) {

        ApiService service = ApiServiceBuilder.buildService(ApiService.class);
        Call<PostItemResponse> call = service.getPosts(FIRST_PAGE);


        call.enqueue(new Callback<PostItemResponse>() {
            @Override
            public void onResponse(Call<PostItemResponse> call, Response<PostItemResponse> response) {
                PostItemResponse apiResponse = response.body();
                if (apiResponse != null) {
                    List<PostItem> responseItems = apiResponse.getPosts();
                    callback.onResult(responseItems, null, FIRST_PAGE + 1);
                }
            }

            @Override
            public void onFailure(Call<PostItemResponse> call, Throwable t) {
            Log.i(TAG,"something went wrong here:initialload",t);
            }
        });


    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params,
                           @NonNull LoadCallback<Long, PostItem> callback) {

        ApiService service = ApiServiceBuilder.buildService(ApiService.class);
        Call<PostItemResponse> call = service.getPosts(FIRST_PAGE);

        call.enqueue(new Callback<PostItemResponse>() {
            @Override
            public void onResponse(Call<PostItemResponse> call, Response<PostItemResponse> response) {
                PostItemResponse apiResponse = response.body();
                if (apiResponse != null) {
                    List<PostItem> responseItems = apiResponse.getPosts();
                    long key;
                    if (params.key > 1) {
                        key = params.key - 1;
                    } else {
                        key = 0;
                    }
                    callback.onResult(responseItems, key);

                }
            }

            @Override
            public void onFailure(Call<PostItemResponse> call, Throwable t) {
                Log.i(TAG,"something went wrong here:initialload",t);
            }
        });

        }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params,
                          @NonNull LoadCallback<Long, PostItem> callback) {

        ApiService service = ApiServiceBuilder.buildService(ApiService.class);
        Call<PostItemResponse> call = service.getPosts(FIRST_PAGE);

        call.enqueue(new Callback<PostItemResponse>() {
            @Override
            public void onResponse(Call<PostItemResponse> call, Response<PostItemResponse> response) {

                PostItemResponse apiResponse = response.body();
                if (apiResponse != null) {
                    List<PostItem> responseItems = apiResponse.getPosts();
                    callback.onResult(responseItems, params.key + 1);
                }

            }

            @Override
            public void onFailure(Call<PostItemResponse> call, Throwable t) {
                Log.i(TAG,"something went wrong here:initialload",t);
            }
        });

    }

}
