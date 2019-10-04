package com.xtrovix.slidingbannerdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BannerImageModel {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("Type")
    @Expose
    private List<TypeModel> type = null;
    @SerializedName("Category")
    @Expose
    private List<CategoryModel> category = null;
    @SerializedName("home_image")
    @Expose
    private List<HomeImageModel> homeImage = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<TypeModel> getType() {
        return type;
    }

    public void setType(List<TypeModel> type) {
        this.type = type;
    }

    public List<CategoryModel> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryModel> category) {
        this.category = category;
    }

    public List<HomeImageModel> getHomeImage() {
        return homeImage;
    }

    public void setHomeImage(List<HomeImageModel> homeImage) {
        this.homeImage = homeImage;
    }

}
