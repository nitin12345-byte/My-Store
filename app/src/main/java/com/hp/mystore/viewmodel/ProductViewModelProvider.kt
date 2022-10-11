package com.hp.mystore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hp.mystore.model.repository.ProductRepository

class ProductViewModelProvider(private val productRepository: ProductRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(productRepository) as T
    }
}