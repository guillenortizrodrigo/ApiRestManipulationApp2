package com.example.apirestmanipulation.Interface;

import com.example.apirestmanipulation.Models.Post;
import com.example.apirestmanipulation.Models.PostRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getposts();

    @POST("posts")
    Call<Post> createpost(@Body PostRequest postRequest);
}
