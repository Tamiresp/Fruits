package com.example.fruits.requests.entity

import com.google.gson.annotations.SerializedName

data class DetailResults(
    @SerializedName("results")
    val list: List<FruitDetail>
)
data class FruitDetail (
    val tfvname: String,
    val botname: String,
    val othname: String,
    val imageurl: String,
    val description: String
)