package com.example.watchudrive.ViewHolders;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.watchudrive.ApiService;
import com.example.watchudrive.BaseViewHolder;
import com.example.watchudrive.CommentVieweActivity;
import com.example.watchudrive.HttpResources;
import com.example.watchudrive.PostItemModel.Comments;
import com.example.watchudrive.PostItemModel.PostItem;
import com.example.watchudrive.R;
import com.example.watchudrive.ReviewActivity;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class VideoViewHolder extends BaseViewHolder {

    PlayerView videoFullScreenPlayer;
    ImageButton imageButtonPlay;
    ImageButton imageButtonPause;
    LinearLayout defaultTimeBar;
    SimpleExoPlayer exoPlayer;
    ImageButton buttonReview;
    Context context;
    PostItem postItem;
    String url;
    CircleImageView circleImageView;
    TextView textView1;
    TextView textView2;
    RequestOptions options;
    View itemView;
    ImageButton imagebbb;
    




    public VideoViewHolder(View itemView) {
        super(itemView);
        this.context = itemView.getContext();
        this.itemView = itemView;
        initComponents();

    }

    public void initComponents() {
        videoFullScreenPlayer = (PlayerView) itemView.findViewById(R.id.id_videoFullScreenPlayer);
        imageButtonPlay = (ImageButton) itemView.findViewById(R.id.exo_play);
        imageButtonPause = (ImageButton) itemView.findViewById(R.id.exo_pause);
        defaultTimeBar = (LinearLayout) itemView.findViewById(R.id.id_timeBar);
        buttonReview = itemView.findViewById(R.id.id_btn_review);
        textView1 = itemView.findViewById(R.id.id_caption_tv);
        textView2 = itemView.findViewById(R.id.id_name_tv);
        circleImageView = itemView.findViewById(R.id.id_uploader_image);
        imagebbb = (ImageButton) itemView.findViewById(R.id.id_imgBtn_review_comment);
    }

    @Override
    protected void clear() {

    }

    public void onBind(PostItem postItem, ApiService apiService) {
        super.onBind(postItem,apiService);

//        options = new RequestOptions().centerCrop()
//                .placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        textView2.setText(postItem.getUploader_name());
        textView1.setText(postItem.getCaption());

       // Glide.with(itemView.getContext()).load(postItem.getProfile_pic_url()).apply(options).into(this.circleImageView);


        try {
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer = ExoPlayerFactory.newSimpleInstance(itemView.getContext(), trackSelector);

            url = HttpResources.IMAGES_BASE_URL+postItem.getPost_url();

            Uri videoURI = Uri.parse(url);

            DefaultHttpDataSourceFactory defaultHttpDataSourceFactory = new DefaultHttpDataSourceFactory("xxx");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource videoSource = new ExtractorMediaSource.Factory(defaultHttpDataSourceFactory).createMediaSource(videoURI);

            videoFullScreenPlayer.setPlayer(exoPlayer);
            videoFullScreenPlayer.setUseController(true);
            defaultTimeBar.setVisibility(View.GONE);
            videoFullScreenPlayer.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_ZOOM);
            exoPlayer.setVideoScalingMode(C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
            videoFullScreenPlayer.setControllerHideOnTouch(false);
            exoPlayer.prepare(videoSource);
            

            imageButtonPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    exoPlayer.setPlayWhenReady(true);
                    videoFullScreenPlayer.setControllerHideOnTouch(true);
                    imageButtonPause.setVisibility(View.GONE);
                    defaultTimeBar.setVisibility(View.VISIBLE);
                }
            });

        } catch (Exception ex) {

        }

        buttonReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ReviewActivity.class);
                intent.putExtra("data",postItem);
                context.startActivity(intent);
            }
        });

        List<Comments> comments = postItem.getComments();
        ArrayList<Comments> comments1 = new ArrayList<Comments>(comments);

        imagebbb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CommentVieweActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) comments);
                intent.putExtras(bundle);
                intent.putExtra("postId",postItem.get_id());
                context.startActivity(intent);
            }
        });
    }
}

