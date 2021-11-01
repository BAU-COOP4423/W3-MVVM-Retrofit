package com.yilmazgokhan.bauexample.data.detail;

import com.google.gson.annotations.SerializedName;

public class Screenshot {
    @SerializedName("id")
    private Integer id;
    @SerializedName("image")
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
