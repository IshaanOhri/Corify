package com.ishaan.corify;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    String BASE_URL = "http://corify.in/";

    @GET("news/india")
    Call<Response> getResponse();

    @GET("news/world")
    Call<Response> getWorld();

    @GET("states")
    Call<IndianStats> getStats();
}
