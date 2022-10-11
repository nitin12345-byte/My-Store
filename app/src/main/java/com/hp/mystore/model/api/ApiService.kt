package com.hp.mystore.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getProductApiInterface(): ProductApiInterface {
        return retrofit.create(ProductApiInterface::class.java)
    }
}