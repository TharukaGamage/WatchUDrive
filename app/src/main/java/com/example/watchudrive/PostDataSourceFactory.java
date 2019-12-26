package com.example.watchudrive;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.watchudrive.PostItemModel.PostItem;

public class PostDataSourceFactory extends DataSource.Factory<Long, PostItem> {

 public MutableLiveData<PostDataSource> postLiveDataSourceMutableLiveData = new MutableLiveData<>();

    @Override
    public DataSource<Long, PostItem> create() {
        PostDataSource postDataSource = new PostDataSource();
        postLiveDataSourceMutableLiveData.postValue(postDataSource);
        return postDataSource;
    }
}
