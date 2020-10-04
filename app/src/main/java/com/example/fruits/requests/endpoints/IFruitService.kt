package com.example.fruits.requests.endpoints

import com.example.fruits.requests.entity.AllResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IFruitService {
    @GET("api/tfvjsonapi.php?/")
    fun getFruits(@Query("search") search: String) : Call<AllResults>
}