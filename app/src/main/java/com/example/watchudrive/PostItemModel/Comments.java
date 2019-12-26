package com.example.watchudrive.PostItemModel;

import com.google.gson.annotations.SerializedName;

public class Comments {
    @SerializedName("clikes")
    private  int clikes;
    @SerializedName("cdislikes")
    private int cdislikes;
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
