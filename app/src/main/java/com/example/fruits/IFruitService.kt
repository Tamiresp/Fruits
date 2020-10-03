package com.example.fruits

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IFruitService {
    @GET("api/tfvjsonapi.php?/search=all")
    fun getFruits() : Call<FindResult>
}