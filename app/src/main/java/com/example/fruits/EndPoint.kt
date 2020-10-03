package com.example.fruits

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {

    @GET("search=all")
    fun getPosts() : Call<List<Fruit>>
}