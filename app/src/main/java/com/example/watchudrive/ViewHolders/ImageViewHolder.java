package com.example.watchudrive.ViewHolders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.solver.ArrayLinkedVariables;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.watchudrive.ApiService;
import com.example.watchudrive.BaseViewHolder;
import com.example.watchudrive.CommentVieweActivity;
import com.example.watchudrive.HttpResources;
import com.example.watchudrive.ImageReviewActivity;
import com.example.watchudrive.NewsFeedActivity;
import com.example.watchudrive.PostItemModel.Comments;
import com.example.watchudrive.PostItemModel.PostItem;
import com.example.watchudrive.PostItemModel.PostItemResponse;
import com.example.watchudrive.R;
import com.example.watchudrive.RegisterActivity;
import com.example.watchudrive.ReviewActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageViewHolder extends BaseViewHolder {

    RequestOptions options;

    ImageView imageView;
    TextView tvName;
    TextView tvDateTime;
    TextView tvCaption;
    CircleImageView uploaderImageView;
    ImageButton imgBtnOptions;
    ImageButton imgBtnLike;
    ImageButton imgBtnComment_Review;
    ImageButton imgBtnComment_Comment;
    String postUrl;
    String uploader_image_url;

    View itemView;
    Context context;

    List<Comments> comments;


    public ImageViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;

        context = itemView.getContext();

        options = new RequestOptions().centerCrop()
                .placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        PostItem postItem;


        imageView = (ImageView) itemView.findViewById(R.id.id_image_v);
        tvCaption = (TextView) itemView.findViewById(R.id.id_tv_caption);
        tvName = (TextView) itemView.findViewById(R.id.id_name_tv);
        tvDateTime = (TextView) itemView.findViewById(R.id.id_time_tv);
        uploaderImageView = (CircleImageView) itemView.findViewById(R.id.id_uploader_image);
        imgBtnOptions = (ImageButton) itemView.findViewById(R.id.id_Image_button_options);
        imgBtnLike = (ImageButton) itemView.findViewById(R.id.id_imageBtn_react);
        imgBtnComment_Comment = (ImageButton) itemView.findViewById(R.id.id_imgBtn_Review);
        imgBtnComment_Review = (ImageButton) itemView.findViewById(R.id.id_imgBtn_review_comment);

    }

    @Override
    protected void clear() {

    }

    public void onBind(PostItem postItem, ApiService apiService) {
        super.onBind(postItem,apiService);

        this.postItem = postItem;

        postUrl = (HttpResources.IMAGES_BASE_URL + postItem.getPost_url());
        uploader_image_url = (HttpResources.PROFILE_PIC_BASE_URL + postItem.getProfile_pic_url());


        tvCaption.setText(postItem.getCaption());
        tvDateTime.setText(postItem.getDate_time());
        tvName.setText(postItem.getUploader_name());


        Glide.with(itemView.getContext()).load(uploader_image_url).apply(options).into(this.uploaderImageView);
        Glide.with(itemView.getContext()).load(postUrl).apply(options).into(this.imageView);

        comments =  postItem.getComments();
        ArrayList<Comments> comments1 = new ArrayList<Comments>(comments);

        imgBtnComment_Review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,CommentVieweActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) comments);
//                intent.putExtras(bundle);
//                intent.putExtra("postId",postItem.get_id());
                intent.putExtra("data",postItem);
                context.startActivity(intent);

            }
        });

        imgBtnComment_Comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ImageReviewActivity.class);
                intent.putExtra("data",postItem);
                context.startActivity(intent);
            }
        });



        imgBtnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                postItem.setLikes(300);

                Call<PostItem> call = apiService.updatePost(postItem.get_id(),postItem);

                call.enqueue(new Callback<PostItem>() {
                    @Override
                    public void onResponse(Call<PostItem> call, Response<PostItem> response) {
                     if(response.isSuccessful())
                     {
                         Toast.makeText(view.getContext(),response.toString(),Toast.LENGTH_SHORT).show();
                     }
                     else {
                         Toast.makeText(view.getContext(),"failed",Toast.LENGTH_SHORT).show();
                     }
                    }

                    @Override
                    public void onFailure(Call<PostItem> call, Throwable t) {
                        Toast.makeText(view.getContext(),"onFailure",Toast.LENGTH_SHORT).show();
                    }
                });
                {

                };
            }
        });

    }
}

