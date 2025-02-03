package com.example.uxassignment1

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.uxassignment1.databinding.ProductItemBinding
import com.google.android.material.chip.Chip

class ProductAdapter(private val products: List<Product>, private val selectedProduct: (Product) -> Unit): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root) {
        val tvProductName: TextView = binding.tvProductName
        val ivProductImage: ImageView = binding.ivProductImage
        val tvProductPrice: TextView = binding.tvProductPrice
        val cProductDiscount: Chip = binding.cProductDiscount
        val tvDiscountedPrice: TextView = binding.tvDiscountedPrice
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
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

        holder.itemView.setOnClickListener {
            selectedProduct(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }
}