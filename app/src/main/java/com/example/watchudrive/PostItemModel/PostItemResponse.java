package com.example.watchudrive.PostItemModel;


import com.google.gson.annotations.SerializedName;

import java.util.List;

 public class PostItemResponse{

    @SerializedName("data")
    private List<PostItem> posts;
    @SerializedName("page")
    private Long page;
    @SerializedName("per_page")
    private Long perPage;
    @SerializedName("total")
    private Long total;
    @SerializedName("total_pages")
    private Long totalPages;

    public List<PostItem> getPosts() {
        return posts;
    }

    public void setPosts(List<PostItem> posts) {
        this.posts = posts;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getPerPage() {
        return perPage;
    }

    public void setPerPage(Long perPage) {
        this.perPage = perPage;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

 }


