package com.hp.mystore.model.repository

import com.hp.mystore.model.ProductResponse
import com.hp.mystore.model.api.ProductApiInterface
import retrofit2.Response

class ProductRepository(private val apiInterface: ProductApiInterface) {
    suspend fun getProducts(): Response<ProductResponse> {
        return apiInterface.getProducts()
    }
}