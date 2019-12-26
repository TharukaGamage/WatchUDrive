package com.example.watchudrive;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.watchudrive.PostItemModel.PostItem;


public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    protected PostItem postItem;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void clear();

    public void onBind(PostItem postItem) {
        this.postItem = postItem;
        clear();
    }

    public PostItem getCurrentPosition() {
        return postItem;
    }
}
