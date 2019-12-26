package com.example.watchudrive.PostItemModel;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PostItem implements Parcelable {

    @SerializedName("uploader_name")
    private String uploader_name;
    @SerializedName("profile_pic_url")
    private String profile_pic_url;
    @SerializedName("date_time")
    private String date_time;
    @SerializedName("reports")
    private int reports;
    @SerializedName("likes")
    private int likes;
    @SerializedName("dislikes")
    private int dislikes;
    @SerializedName("post_url")
    private String post_url;
    @SerializedName("en_revs")
    private boolean en_revs;
    @SerializedName("hide_status")
    private boolean hide_status;
    @SerializedName("num_reviews")
    private int num_reviews;
    @SerializedName("_id")
    private String _id;
    @SerializedName("uploader_id")
    private String uploader_id;
    @SerializedName("caption")
    private String caption;
    @SerializedName("post_type")
    private String post_type;
    @SerializedName("comments")
    private List<Comments> comments;


    protected PostItem(Parcel in) {
        uploader_name = in.readString();
        profile_pic_url = in.readString();
        date_time = in.readString();
        reports = in.readInt();
        likes = in.readInt();
        dislikes = in.readInt();
        post_url = in.readString();
        en_revs = in.readByte() != 0;
        hide_status = in.readByte() != 0;
        num_reviews = in.readInt();
        _id = in.readString();
        uploader_id = in.readString();
        caption = in.readString();
        post_type = in.readString();
        comments = in.createTypedArrayList(Comments.CREATOR);
    }

    public static final Creator<PostItem> CREATOR = new Creator<PostItem>() {
        @Override
        public PostItem createFromParcel(Parcel in) {
            return new PostItem(in);
        }

        @Override
        public PostItem[] newArray(int size) {
            return new PostItem[size];
        }
    };

    public String getUploader_name() {
        return uploader_name;
    }

    public void setUploader_name(String uploader_name) {
        this.uploader_name = uploader_name;
    }

    public String getProfile_pic_url() {
        return profile_pic_url;
    }

    public void setProfile_pic_url(String profile_pic_url) { this.profile_pic_url = profile_pic_url; }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public int getReports() {
        return reports;
    }

    public void setReports(int reports) {
        this.reports = reports;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getPost_url() {
        return post_url;
    }

    public void setPost_url(String post_url) {
        this.post_url = post_url;
    }

    public boolean isEn_revs() {
        return en_revs;
    }

    public void setEn_revs(boolean en_revs) {
        this.en_revs = en_revs;
    }

    public boolean isHide_status() {
        return hide_status;
    }

    public void setHide_status(boolean hide_status) {
        this.hide_status = hide_status;
    }

    public int getNum_reviews() {
        return num_reviews;
    }

    public void setNum_reviews(int num_reviews) {
        this.num_reviews = num_reviews;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUploader_id() {
        return uploader_id;
    }

    public void setUploader_id(String uploader_id) {
        this.uploader_id = uploader_id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPost_type() {
        return post_type;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public List<Comments> getComments() { return comments; }

    public void setComments(List<Comments> comments) { this.comments = comments; }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(uploader_name);
        parcel.writeString(profile_pic_url);
        parcel.writeString(date_time);
        parcel.writeInt(reports);
        parcel.writeInt(likes);
        parcel.writeInt(dislikes);
        parcel.writeString(post_url);
        parcel.writeByte((byte) (en_revs ? 1 : 0));
        parcel.writeByte((byte) (hide_status ? 1 : 0));
        parcel.writeInt(num_reviews);
        parcel.writeString(_id);
        parcel.writeString(uploader_id);
        parcel.writeString(caption);
        parcel.writeString(post_type);
        parcel.writeTypedList(comments);
    }
}
