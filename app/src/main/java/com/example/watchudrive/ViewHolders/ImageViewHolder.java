package com.example.watchudrive.ViewHolders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.watchudrive.BaseViewHolder;
import com.example.watchudrive.HttpResources;
import com.example.watchudrive.PostItemModel.PostItem;
import com.example.watchudrive.PostItemModel.PostItemResponse;
import com.example.watchudrive.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

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

    String postUrl;
    String uploader_image_url;

    public ImageViewHolder(View itemView) {
        super(itemView);
        options = new RequestOptions().centerCrop()
                .placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);


        imageView = (ImageView) itemView.findViewById(R.id.id_image_v);
        tvCaption = (TextView) itemView.findViewById(R.id.id_tv_caption);
        tvName = (TextView) itemView.findViewById(R.id.id_name_tv);
        tvDateTime = (TextView) itemView.findViewById(R.id.id_time_tv);
        uploaderImageView = (CircleImageView) itemView.findViewById(R.id.id_uploader_image);
        imgBtnOptions = (ImageButton) itemView.findViewById(R.id.id_Image_button_options);
        imgBtnLike = (ImageButton) itemView.findViewById(R.id.id_imageBtn_react);
        imgBtnComment_Review = (ImageButton) itemView.findViewById(R.id.id_imgBtn_review_comment);

    }

    @Override
    protected void clear() {

    }

    public void onBind(PostItem postItem) {
        super.onBind(postItem);

        postUrl = (HttpResources.IMAGES_BASE_URL + postItem.getPost_url());
        uploader_image_url = (HttpResources.PROFILE_PIC_BASE_URL + postItem.getProfile_pic_url());

        tvCaption.setText(postItem.getCaption());
        tvDateTime.setText(postItem.getDate_time());
        tvName.setText(postItem.getUploader_name());


        Glide.with(itemView.getContext()).load(uploader_image_url).apply(options).into(this.uploaderImageView);
        Glide.with(itemView.getContext()).load(postUrl).apply(options).into(this.imageView);
    }
}

