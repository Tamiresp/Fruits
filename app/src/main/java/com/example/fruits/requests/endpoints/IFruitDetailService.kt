package com.example.fruits.requests.endpoints

import com.example.fruits.requests.entity.DetailResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IFruitDetailService {
    @GET("api/tfvjsonapi.php?/")
    fun getFruitDetail(@Query("tfvitem") tfvitem: String) : Call<DetailResults>
}