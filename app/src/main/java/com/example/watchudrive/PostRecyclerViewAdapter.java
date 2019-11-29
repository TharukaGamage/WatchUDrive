package com.example.watchudrive;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;

import com.example.watchudrive.PostItemModel.PostItem;
import com.example.watchudrive.ViewHolders.ImageViewHolder;
import com.example.watchudrive.ViewHolders.LoadingItemViewHolder;
import com.example.watchudrive.ViewHolders.VideoViewHolder;

public class PostRecyclerViewAdapter extends PagedListAdapter<PostItem, BaseViewHolder> {

    private final static int VIEW_TYPE_VIDEO =2;
    private final static int VIEW_TYPE_IMAGE =1;
    private final static int VIEW_TYPE_LOADING =0;
    public static String TAG = PostDataSource.class.getSimpleName();

    protected PostRecyclerViewAdapter() {
        super(USER_COMPARATOR);
        Log.i(TAG,"something went wrong here:initialload");
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
            default : return new LoadingItemViewHolder(LayoutInflater
                    .from(parent.getContext()).inflate(R.layout.item_loading,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
         holder.onBind(getItem(position));
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
