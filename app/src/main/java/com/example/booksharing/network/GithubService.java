package com.example.booksharing.network;

import com.example.booksharing.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubService {
    @GET("/users/{id}/posts")
    Call<List<Post>> getAllPhotos(@Query("id") int id);
}
