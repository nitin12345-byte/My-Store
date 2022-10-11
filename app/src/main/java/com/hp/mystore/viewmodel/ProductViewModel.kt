package com.hp.mystore.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hp.mystore.model.NetworkResult
import com.hp.mystore.model.repository.ProductRepository
import com.hp.mystore.model.ProductResponse
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {

    private val productListLiveData = MutableLiveData<NetworkResult<ProductResponse>>()

    fun getProductListLiveData(): LiveData<NetworkResult<ProductResponse>> {
        return productListLiveData
    }

    fun getProducts() {
        productListLiveData.value = NetworkResult.Loading()

        viewModelScope.launch {
            try {
                val products = productRepository.getProducts()
                if (products.isSuccessful) {
                    productListLiveData.value = NetworkResult.Success(products.body())
                } else {
                    productListLiveData.value =
                        NetworkResult.Failure(products.message())
                }
            } catch (exception: Exception) {
                Log.e(TAG, "Error : ${exception.message}")
                productListLiveData.value =
                    NetworkResult.Failure(exception.message)
            }
        }
    }

    companion object {
        private val TAG = ProductViewModel::class.simpleName
    }

}