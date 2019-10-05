package com.example.booksharing.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseModel {

    @SerializedName("data")
    List<dataItem> dataItem;

    public List<dataItem> getDataItem() {
        return dataItem;
    }

    public void setDataItem(List<dataItem> dataItem) {
        this.dataItem = dataItem;
    }
}
