package com.example.watchudrive.ViewHolders;

import android.net.Uri;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.watchudrive.BaseViewHolder;
import com.example.watchudrive.HttpResources;
import com.example.watchudrive.PostItemModel.PostItem;
import com.example.watchudrive.R;
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

public class VideoViewHolder extends BaseViewHolder {

    PlayerView videoFullScreenPlayer;
    ImageButton imageButtonPlay;
    ImageButton imageButtonPause;
    LinearLayout defaultTimeBar;

    SimpleExoPlayer exoPlayer;


    public VideoViewHolder(View itemView) {
        super(itemView);
        initComponents();
    }

    public void initComponents() {
        videoFullScreenPlayer = (PlayerView) itemView.findViewById(R.id.id_videoFullScreenPlayer);
        imageButtonPlay = (ImageButton) itemView.findViewById(R.id.exo_play);
        imageButtonPause = (ImageButton) itemView.findViewById(R.id.exo_pause);
        defaultTimeBar = (LinearLayout) itemView.findViewById(R.id.id_timeBar);
    }

    @Override
    protected void clear() {

    }

    public void onBind(PostItem postItem) {

        try {
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer = ExoPlayerFactory.newSimpleInstance(itemView.getContext(), trackSelector);

            Uri videoURI = Uri.parse(HttpResources.VIDEO_BASE_URL);

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
    }
}

