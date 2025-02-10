package com.example.uxassignment1.ui

import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.uxassignment1.R
import com.example.uxassignment1.databinding.ProductCardBinding
import com.example.uxassignment1.databinding.ProductItemBinding
import com.example.uxassignment1.ui.ProductAdapter.ProductViewHolder
import com.google.android.material.chip.Chip
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity

class ShareProductAdapter(private val products: MutableList<Product>, private val viewPager2: ViewPager2, private val selectedProduct: (Product) -> Unit) :
    RecyclerView.Adapter<ShareProductAdapter.ShareProductViewHolder>() {

        class ShareProductViewHolder(binding: ProductCardBinding): RecyclerView.ViewHolder(binding.root) {
            val tvProductName: TextView = binding.tvProductName
            val ivProductImage: ImageView = binding.ivProductImage
            val tvProductPrice: TextView = binding.tvProductPrice
            val cProductDiscount: Chip = binding.cProductDiscount
            val tvDiscountedPrice: TextView = binding.tvDiscountedPrice
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShareProductViewHolder {
        val binding = ProductCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShareProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShareProductViewHolder, position: Int) {
        val currentItem = products[position]

        holder.tvProductName.text = currentItem.name
        holder.ivProductImage.setImageResource(currentItem.image)
        holder.tvProductPrice.text = currentItem.price

        if (currentItem.discount != "0%") {
            holder.cProductDiscount.text = currentItem.discount
            holder.tvDiscountedPrice.text = currentItem.discountedPrice
            holder.tvDiscountedPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.cProductDiscount.isVisible = false
            holder.tvDiscountedPrice.isVisible = false

        }

        if (position == products.size - 1) {
            viewPager2.post(runnable)
        }

        holder.itemView.setOnClickListener {
            Log.d("ShareProduct", "Product clicked: ${currentItem.name}")
            selectedProduct(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    private val runnable = Runnable {
//        products.clear()
//        products.addAll(products)
        notifyDataSetChanged()
    }

}