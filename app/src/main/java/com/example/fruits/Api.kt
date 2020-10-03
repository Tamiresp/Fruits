package com.example.fruits

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {
    private val httpClient = OkHttpClient.Builder()
    private val gson = GsonBuilder().create()
    private var retrofit = Retrofit.Builder()
        .baseUrl("http://tropicalfruitandveg.com")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun getInstance() : IFruitService {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(logging)

        return retrofit.create(IFruitService::class.java)
    }
}