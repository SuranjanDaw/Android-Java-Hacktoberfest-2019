package com.example.booksharing.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface endpoints {

    @GET("5d8f20833200004d00adeb82")
    Call<responseModel> getImages();
}
