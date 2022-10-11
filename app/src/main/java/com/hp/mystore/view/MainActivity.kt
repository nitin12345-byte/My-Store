package com.hp.mystore.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hp.mystore.R
import com.hp.mystore.databinding.ActivityMainBinding
import com.hp.mystore.model.api.ApiService
import com.hp.mystore.model.NetworkResult
import com.hp.mystore.model.Product
import com.hp.mystore.model.repository.ProductRepository
import com.hp.mystore.view.adapter.ProductsAdapter
import com.hp.mystore.viewmodel.ProductViewModel
import com.hp.mystore.viewmodel.ProductViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ProductViewModel
    private lateinit var binding: ActivityMainBinding
    private var productsList = mutableListOf<Product>()
    private lateinit var adapter: ProductsAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerview()
        initViewModel()
        setupObserver()
        setApplicationLogo()
        // calling this method to make api call
        viewModel.getProducts()
    }

    private fun setApplicationLogo() {
        supportActionBar?.run {
            setDisplayShowHomeEnabled(true)
            setDisplayUseLogoEnabled(true)
            setLogo(R.drawable.ic_app_logo)
        }
    }


    private fun setupRecyclerview() {
        adapter = ProductsAdapter(this, productsList)

        binding.rvProducts.apply {
            this.adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel() {
        val apiInterface = ApiService.getProductApiInterface()
        viewModel =
            ViewModelProvider(
                this,
                ProductViewModelProvider(ProductRepository(apiInterface))
            )[ProductViewModel::class.java]
    }

    fun setupObserver() {
        viewModel.getProductListLiveData().observe(this) { it ->
            when (it) {
                is NetworkResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is NetworkResult.Success -> {
                    binding.progressBar.visibility = View.GONE

                    it.data?.products?.let {
                        productsList.clear()
                        productsList.addAll(it)
                        adapter.notifyDataSetChanged()
                        binding.rvProducts.visibility = View.VISIBLE
                    }
                }
                is NetworkResult.Failure -> {
                    Log.e(TAG, "Error : ")
                    showErrorDialog()
                    binding.progressBar.visibility = View.GONE
                    binding.tvNoResults.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun showErrorDialog() {
        val alertDialog = AlertDialog.Builder(this)
            .setMessage(R.string.error_msg)
            .setCancelable(false)
            .setPositiveButton(R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }.create()
        alertDialog.show()
    }

    companion object {
        private val TAG = MainActivity::class.simpleName
    }
}