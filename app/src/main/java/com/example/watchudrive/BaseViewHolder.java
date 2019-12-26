package com.example.watchudrive;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.watchudrive.PostItemModel.Comments;
import com.example.watchudrive.PostItemModel.PostItem;

import java.util.List;


public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    protected PostItem postItem;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void clear();

    public void onBind(PostItem postItem,ApiService apiService) {
        this.postItem = postItem;

        clear();
    }

    public PostItem getCurrentPosition() {
        return postItem;
    }
}
