package com.example.news_api_application

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface  {
    @GET("everything")
    fun getNewsArticles(
        @Query("q") keyword : String,
        @Query("apiKey") apiKey : String
    ) : Call<MyData>
}