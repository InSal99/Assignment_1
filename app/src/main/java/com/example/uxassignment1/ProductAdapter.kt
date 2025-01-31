package com.example.uxassignment1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uxassignment1.ServiceAdapter.ServiceViewHolder
import com.google.android.material.chip.Chip

class ProductAdapter(private val products: List<Product>): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvProductName: TextView = itemView.findViewById(R.id.tvProductName)
        val ivProductImage: ImageView = itemView.findViewById(R.id.ivProductImage)
        val tvProductPrice: TextView = itemView.findViewById(R.id.tvProductPrice)
        val cProductDiscount: Chip = itemView.findViewById(R.id.cProductDiscount)
        val tvDiscountedPrice: TextView = itemView.findViewById(R.id.tvDiscountedPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = products[position]
        holder.tvProductName.text = currentItem.name
        holder.ivProductImage.setImageResource(currentItem.image)
        holder.tvProductPrice.text = currentItem.price
        holder.cProductDiscount.text = currentItem.discount
        holder.tvDiscountedPrice.text = currentItem.discountedPrice
    }

    override fun getItemCount(): Int {
        return products.size
    }
}