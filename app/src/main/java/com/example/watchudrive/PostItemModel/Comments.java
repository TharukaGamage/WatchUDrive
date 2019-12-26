package com.example.watchudrive.PostItemModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Comments implements Parcelable {

    @SerializedName("clikes")
    private  int clikes;
    @SerializedName("cdislikes")
    private int cdislikes;

    public Comments(int clikes, int cdislikes,
                    String c_id, String cprofile_id,
                    String cname, String cprof_pic_url,
                    String comment, String cDateTime) {
        this.clikes = clikes;
        this.cdislikes = cdislikes;
        this.c_id = c_id;
        this.cprofile_id = cprofile_id;
        this.cname = cname;
        this.cprof_pic_url = cprof_pic_url;
        this.comment = comment;
        this.cDateTime = cDateTime;
    }

    @SerializedName("_id")
    private  String c_id;
    @SerializedName("cprofile_id")
    private  String cprofile_id;
    @SerializedName("cname")
    private  String cname;
    @SerializedName("cprof_pic_url")
    private  String cprof_pic_url;
    @SerializedName("comment")
    private  String comment;
    @SerializedName("cDateTime")
    private String cDateTime;


    public static final Creator<Comments> CREATOR = new Creator<Comments>() {
        @Override
        public Comments createFromParcel(Parcel in) {
            return new Comments(in);
        }

        @Override
        public Comments[] newArray(int size) {
            return new Comments[size];
        }
    };


    public Comments(Parcel in){

        clikes = in.readInt();
        cdislikes = in.readInt();
        c_id = in.readString();
        cprofile_id = in.readString();
        cname = in.readString();
        cprof_pic_url = in.readString();
        comment = in.readString();
        cDateTime = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(clikes);
        parcel.writeInt(cdislikes);
        parcel.writeString(c_id);
        parcel.writeString(cprofile_id);
        parcel.writeString(cname);
        parcel.writeString(cprof_pic_url);
        parcel.writeString(comment);
        parcel.writeString(cDateTime);
    }


    public int getClikes() {
        return clikes;
    }

    public void setClikes(int clikes) {
        this.clikes = clikes;
    }

    public int getCdislikes() {
        return cdislikes;
    }

    public void setCdislikes(int cdislikes) {
        this.cdislikes = cdislikes;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getCprofile_id() {
        return cprofile_id;
    }

    public void setCprofile_id(String cprofile_id) {
        this.cprofile_id = cprofile_id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCprof_pic_url() {
        return cprof_pic_url;
    }

    public void setCprof_pic_url(String cprof_pic_url) {
        this.cprof_pic_url = cprof_pic_url;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getcDateTime() {
        return cDateTime;
    }

    public void setcDateTime(String cDateTime) {
        this.cDateTime = cDateTime;
    }


}
