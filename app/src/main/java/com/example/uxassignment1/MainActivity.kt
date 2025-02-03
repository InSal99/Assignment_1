package com.example.uxassignment1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uxassignment1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var serviceAdapter: ServiceAdapter
    private lateinit var services: List<Service>
    private lateinit var productAdapter: ProductAdapter
    private lateinit var products: List<Product>
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        services = listOf(
            Service("Tambah krim kocok", "+10.000", false),
            Service("Tambah es krim", "+5.000", false)
        )
        Log.d("MyActivity", "Services List: ${services.size}")

        products = listOf(
            Product("Dark Hojicha Sweet Potato Parfait", R.drawable.product_1, "Rp89.000", "0%", "Rp89.000"),
            Product("Very Berry Pavlovas", R.drawable.product_2, "Rp55.000", "7%", "Rp59.000"),
            Product("Egg Scones", R.drawable.product_3, "Rp138.500", "10%", "Rp216.000"),
            Product("Autumn Fatcarons", R.drawable.product_4, "Rp285.000", "5%", "Rp299.000"),
            Product("Poured Matcha Tiramisu", R.drawable.product_current, "Rp77.000", "10%", "Rp85.000")
        )
        Log.d("MyActivity", "Product List: ${products.size}")

        binding.rvServingList.layoutManager = LinearLayoutManager(this)
        serviceAdapter = ServiceAdapter(services) { selectedService ->
            services.forEach { it.isChecked = false }
            selectedService.isChecked = true
            serviceAdapter.notifyDataSetChanged()
        }
        binding.rvServingList.adapter = serviceAdapter

        binding.rvSimilarProducts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        productAdapter = ProductAdapter(products) { selectedProduct ->
            val intent = Intent(this, NavigationActivity::class.java)

            // Passing the data (let's assume `selectedItem` has a property `id` and `name`)
            intent.putExtra("productName", selectedProduct.name)
            intent.putExtra("productImage", selectedProduct.image)
            intent.putExtra("productPrice", selectedProduct.price)
            intent.putExtra("productDiscount", selectedProduct.discount)
            intent.putExtra("productDiscountedPrice", selectedProduct.discountedPrice)

            startActivity(intent)
        }
        binding.rvSimilarProducts.adapter = productAdapter

        // Create the item decoration with desired space (in pixels)
        val startSpacingDecoration = StartSpacingItemDecoration(this, 12)
        binding.rvSimilarProducts.addItemDecoration(startSpacingDecoration)






        /*------------------- Trial Fragments -----------------------*/
        //go to fragments
        binding.btnBack.setOnClickListener{
            val destination = Intent(this, NavigationActivity::class.java)
            startActivity(destination)
        }

    }
}
