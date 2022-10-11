package com.hp.mystore.model.api

import com.hp.mystore.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductApiInterface {
    @GET("/products")
    suspend fun getProducts(): Response<ProductResponse>
}