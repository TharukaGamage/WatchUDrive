package com.example.watchudrive;

import android.media.Image;
import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.watchudrive.PostItemModel.Comments;
import com.example.watchudrive.PostItemModel.PostItem;
import com.example.watchudrive.PostItemModel.PostItemResponse;
import com.example.watchudrive.PostItemModel.Review;
import com.example.watchudrive.ViewHolders.ImageViewHolder;

import java.util.List;

import retrofit2.Call;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.MyViewHolder> {

    private  String postId;
    private List<Comments> mComments;
    private  ImageView imageView;
    ApiService apiService;
    private PostItem postItem;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        RequestOptions options;
        public TextView tvComment;
        public TextView tvLikes;
        private View view;
        private Button btnLike;
        private EditText editText;
        private  Button btnComment;


        ApiService apiService;


        public MyViewHolder(View view) {
            super(view);
            this.view = view;

            apiService = ApiServiceBuilder.buildService(ApiService.class);


            options = new RequestOptions().centerCrop()
                    .placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);


            tvComment = view.findViewById(R.id.tvComment);
            tvLikes = view.findViewById(R.id.tvlikes);
            imageView = view.findViewById(R.id.ivImage);
            btnLike = view.findViewById(R.id.btnLike);
            editText = view.findViewById(R.id.editText2);
            btnComment = view.findViewById(R.id.buttonComment);



        }

        public void setImage(String url,int likes){
            Glide.with(view.getContext()).load(HttpResources.PROFILE_PIC_BASE_URL+url).apply(options).into(imageView);

            tvLikes.setText("Likes "+Integer.toString(likes));

            btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tvLikes.setText("Likes "+Integer.toString(likes+1));
                }
            });

        }

        public void postComment(PostItem postItem){

//            Comments comments = new Comments(0,0,"001","asadasd","tharuka","asdasdsad",editText.getText().toString(),"2019/12/13");
//            postItem.setComments(comments);
//            apiService = ApiServiceBuilder.buildService(ApiService.class);
//
//            Call<Review> call = apiService.postComment(postItem.);
        }
    }


    public CommentsAdapter(PostItem postItem) {

        this.postItem = postItem;
        mComments = postItem.getComments();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_view_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Comments comments = mComments.get(position);
        holder.tvComment.setText(comments.getComment());
        holder.setImage(comments.getCprof_pic_url(),comments.getCdislikes());
        holder.postComment(postItem);
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }
}