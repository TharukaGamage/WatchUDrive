package com.example.watchudrive;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.watchudrive.PostItemModel.PostItemResponse;
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

import java.util.List;

/*public class PostRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder>  {
    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private static final int VIEW_TYPE_TEXT = 2;
    private  static final int VIEW_TYPE_UPLOD = 3;
    private boolean isLoaderVisible = false;
    RequestOptions options;

    private List<PostItemResponse.PostItem> mPostItems;
    Context mContext;


    public PostRecyclerAdapter(List<PostItemResponse.PostItem> postItems, Context context) {
        this.mPostItems = postItems;
        this.mContext = context;
        options = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
       // getItemViewType(0);
    }

    @NonNull
    @Override
    public  BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false));
            case VIEW_TYPE_LOADING:
                return new ProgressHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false));
                        //LayoutInflater.from(parent.getContext()).inflate(R.layout.upload_image_video_item, parent, false));
            case VIEW_TYPE_TEXT:
                return new TextHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item_new, parent, false));
            case VIEW_TYPE_UPLOD:
                return new PostItemholder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.upload_image_video_item, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {

        String s = mPostItems.get(position).getType();

        if (isLoaderVisible) {

            if(position == mPostItems.size()-1){

                return VIEW_TYPE_LOADING;
            }
            else {
                if(position==0){

                    return VIEW_TYPE_UPLOD;

                }

                if(s.equals("text")){

                    return VIEW_TYPE_TEXT;
                }

                else {
                    return VIEW_TYPE_NORMAL;
                }
            }
        }

        else {
            return VIEW_TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return mPostItems == null ? 0 : mPostItems.size();
    }

    public void addItems(List<PostItemResponse.PostItem> postItems) {
        mPostItems.addAll(postItems);
        notifyDataSetChanged();
    }

    public void addLoading() {
        isLoaderVisible = true;
        mPostItems.add(new PostItemResponse.PostItem());
        notifyItemInserted(mPostItems.size() - 1);
    }

    public void removeLoading() {
        isLoaderVisible = false;
        int position = mPostItems.size() - 1;
        PostItemResponse.PostItem item = getItem(position);
        if (item != null) {
            mPostItems.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        mPostItems.clear();
        notifyDataSetChanged();
    }

    PostItemResponse.PostItem getItem(int position) {
        return mPostItems.get(position);
    }

    public class ViewHolder extends BaseViewHolder {
        @BindView(R.id.id_name_tv)
        TextView textViewTitle;
        @BindView(R.id.id_time_tv)
        TextView textViewDescription;
        @BindView(R.id.id_image_v)
        ImageView imageView;



        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {

        }

        public void onBind(int position) {
            super.onBind(position);
            PostItemResponse.PostItem item = mPostItems.get(position);

            textViewTitle.setText(item.getTitle());
            textViewDescription.setText(item.getDescription());
            Glide.with(mContext).load(mPostItems.get(position).getImage_url()).apply(options).into(this.imageView);
        }
    }

    public class ProgressHolder extends BaseViewHolder {
        ProgressHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {
        }
    }

    public class TextHolder extends BaseViewHolder {

        @BindView(R.id.videoFullScreenPlayer)PlayerView videoFullScreenPlayer;
      //  @BindView(R.id.spinnerVideoDetails)ProgressBar spinnerVideoDetails;
        @BindView(R.id.exo_play)ImageButton imageButtonPlay;
        @BindView(R.id.exo_pause)ImageButton imageButtonPause;
       @BindView(R.id.id_timeBar) LinearLayout defaultTimeBar;

        SimpleExoPlayer exoPlayer;

        String Url;
        Context context;


        public TextHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        protected void clear() {

        }

        public void onBind(int position) {
            super.onBind(position);
            PostItemResponse.PostItem item = mPostItems.get(position);
            try {
                BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
                TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
                exoPlayer = ExoPlayerFactory.newSimpleInstance(mContext, trackSelector);

                Uri videoURI = Uri.parse(item.getImage_url());

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

    public class PostItemholder extends BaseViewHolder{

        @BindView(R.id.id_btn_upload)ImageButton imageButtonUpload;

        public PostItemholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        protected void clear() {

        }


        public void onBind(int position){
            super.onBind(position);
            PostItemResponse.PostItem item = mPostItems.get(position);
        }
    }
}*/
