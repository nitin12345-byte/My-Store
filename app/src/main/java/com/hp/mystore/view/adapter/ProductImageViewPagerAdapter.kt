package com.hp.mystore.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hp.mystore.R
import com.hp.mystore.databinding.ProductImageItemBinding

class ProductImageViewPagerAdapter(
    private val context: Context,
    private val imagesList: List<String>
) :
    RecyclerView.Adapter<ProductImageViewPagerAdapter.ProductImageViewHolder>() {

    class ProductImageViewHolder(val binding: ProductImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImageViewHolder {
        val binding =
            ProductImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductImageViewHolder, position: Int) {
        Glide.with(context).load(imagesList[position]).error(R.drawable.ic_image_not_available)
            .into(holder.binding.ivProductImage)
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }
}