package com.example.fruits.requests.entity

import com.google.gson.annotations.SerializedName


data class AllResults(
    @SerializedName("results")
    val list: List<Fruit>
)

data class Fruit (
    val tfvname: String,
    val imageurl: String
)

