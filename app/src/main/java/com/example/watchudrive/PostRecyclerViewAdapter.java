package com.example.watchudrive;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;

import com.example.watchudrive.PostItemModel.Comments;
import com.example.watchudrive.PostItemModel.PostItem;
import com.example.watchudrive.ViewHolders.ImageViewHolder;
import com.example.watchudrive.ViewHolders.LoadingItemViewHolder;
import com.example.watchudrive.ViewHolders.UploadItemViewHolder;
import com.example.watchudrive.ViewHolders.VideoViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

public class PostRecyclerViewAdapter extends PagedListAdapter<PostItem, BaseViewHolder> {

    private final static int VIEW_TYPE_VIDEO =2;
    private final static int VIEW_TYPE_IMAGE =1;
    private final static int VIEW_TYPE_LOADING =0;
    private final  static int VIEW_TYPE_UPLOAD = 4;

    public static String TAG = PostDataSource.class.getSimpleName();

    ApiService apiService;

    protected PostRecyclerViewAdapter() {
        super(USER_COMPARATOR);
        Log.i(TAG,"something went wrong here:initialload");
        apiService = ApiServiceBuilder.buildService(ApiService.class);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType){
            case 1:
                return new ImageViewHolder(LayoutInflater
                        .from(parent.getContext()).inflate(R.layout.image_item,parent,false));
            case 2:return new VideoViewHolder(LayoutInflater
                    .from(parent.getContext()).inflate(R.layout.video_item_new,parent,false));
            case 4:return new UploadItemViewHolder(LayoutInflater
                    .from(parent.getContext()).inflate(R.layout.upload_image_video_item,parent,false));
            default : return new LoadingItemViewHolder(LayoutInflater
                    .from(parent.getContext()).inflate(R.layout.item_loading,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
         holder.onBind(getItem(position),apiService);
    }


    @Override
    public int getItemViewType(int position) {

        String itemType = getItem(position).getPost_type();

        if(itemType.equals("image")){
            return VIEW_TYPE_IMAGE;
        }
        if(itemType.equals("video")){
            return VIEW_TYPE_VIDEO;
        }
        if(itemType.equals("upload")){
            return VIEW_TYPE_UPLOAD;
        }
       else
        return VIEW_TYPE_LOADING;
    }

    private static final DiffUtil.ItemCallback<PostItem> USER_COMPARATOR = new DiffUtil.ItemCallback<PostItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull PostItem oldItem, @NonNull PostItem newItem) {
            return oldItem.get_id() == newItem.get_id();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull PostItem oldItem, @NonNull PostItem newItem) {
            return oldItem == newItem;
        }

    };

    }
