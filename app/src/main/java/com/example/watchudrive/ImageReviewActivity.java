package com.example.watchudrive;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
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

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageReviewActivity extends AppCompatActivity {

    ImageView imageView;
    PostItem postItem;
    Button button;
    EditText editText1;
    EditText editText2;
    RequestOptions options;
    CircleImageView circleImageView;

    ApiService apiService;

    String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_review);

        options = new RequestOptions().centerCrop()
                .placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        button = findViewById(R.id.btnReport);
        editText1 = findViewById(R.id.tvLicenPlate);
        editText2 = findViewById(R.id.tvviolation);
        imageView = findViewById(R.id.imageView);
        circleImageView = findViewById(R.id.id_profile_uploader);

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


       String postUrl = (HttpResources.IMAGES_BASE_URL + postItem.getPost_url());
       String uploader_image_url = (HttpResources.PROFILE_PIC_BASE_URL + postItem.getProfile_pic_url());
       Glide.with(this).load(uploader_image_url).apply(options).into(this.circleImageView);
        Glide.with(this).load(postUrl).apply(options).into(this.imageView);


    }
}
