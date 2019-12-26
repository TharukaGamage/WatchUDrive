package com.example.watchudrive;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.watchudrive.PostItemModel.PostItem;
import com.example.watchudrive.PostItemModel.Review;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewActivity extends AppCompatActivity {

    PlayerView videoFullScreenPlayer;
    ImageButton imageButtonPlay;
    ImageButton imageButtonPause;
    LinearLayout defaultTimeBar;

    SimpleExoPlayer exoPlayer;

    PostItem postItem;

    Button button;
    EditText editText1;
    EditText editText2;

    ApiService apiService;

    String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_review);

        button = findViewById(R.id.btnReport);
        editText1 = findViewById(R.id.tvLicenPlate);
        editText2 = findViewById(R.id.tvviolation);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiService = ApiServiceBuilder.buildService(ApiService.class);

                Review review = new Review(editText1.getText().toString(),editText2.getText().toString(),"125","2019/12/13");

                Call<Review> call = apiService.postReview(review);

                call.enqueue(new Callback<Review>() {
                    @Override
                    public void onResponse(Call<Review> call, Response<Review> response) {

                    }

                    @Override
                    public void onFailure(Call<Review> call, Throwable t) {

                    }
                });
            }
        });




        Intent i = getIntent();
         postItem = (PostItem) i.getParcelableExtra("data");


        videoFullScreenPlayer = (PlayerView) findViewById(R.id.id_videoFullScreenPlayer);
        imageButtonPlay = (ImageButton) findViewById(R.id.exo_play);
        imageButtonPause = (ImageButton) findViewById(R.id.exo_pause);
        defaultTimeBar = (LinearLayout) findViewById(R.id.id_timeBar);

        try {
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

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

    }
}
