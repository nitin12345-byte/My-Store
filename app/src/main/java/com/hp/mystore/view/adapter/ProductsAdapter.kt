package com.hp.mystore.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.hp.mystore.databinding.ProductItemBinding
import com.hp.mystore.model.Product

class ProductsAdapter(private val context: Context, private val productsList: List<Product>) :
    RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    class ProductViewHolder(val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productsList[position]
        holder.binding.apply {
            this.product = product
            vpProductImage.adapter =
                ProductImageViewPagerAdapter(context, product.images)
            TabLayoutMediator(tlProductImage, vpProductImage) { _, _ -> }.attach()
        }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }
}