package com.example.booksharing.retrofit;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class dataItem implements Serializable {

	@SerializedName("place")
	private String place;

	@SerializedName("url")
	private String url;

	public void setPlace(String place){
		this.place = place;
	}

	public String getPlace(){
		return place;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}


}