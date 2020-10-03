package com.example.fruits

import com.google.gson.annotations.SerializedName


data class FindResult(
    @SerializedName("results")
    val list: List<Fruit>
)

data class Fruit (
    val tfvname: String,
    val botname: String,
    val othname: String,
    val imageurl: String
)