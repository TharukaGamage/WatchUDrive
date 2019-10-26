package com.example.watchudrive;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.watchudrive.PostItemModel.PostItem;

public class PostViewModel extends ViewModel {

   public LiveData<PagedList<PostItem>>  postpagedList;

   private  LiveData<PostDataSource> postDataSourceLiveData;

    public PostViewModel() {
        init();
    }

    public void init(){
        PostDataSourceFactory postDataSourceFactory =new PostDataSourceFactory();
        postDataSourceLiveData = postDataSourceFactory.postLiveDataSourceMutableLiveData;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PostDataSource.PAGE_SIZE)
                .build();

        postpagedList = new LivePagedListBuilder<>(postDataSourceFactory, config).build();
    }
}
