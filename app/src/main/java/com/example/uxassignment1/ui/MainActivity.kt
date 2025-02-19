package com.example.uxassignment1.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ModalBottomSheet
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uxassignment1.R
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

            intent.putExtra("productName", selectedProduct.name)
            intent.putExtra("productImage", selectedProduct.image)
            intent.putExtra("productPrice", selectedProduct.price)
            intent.putExtra("productDiscount", selectedProduct.discount)
            intent.putExtra("productDiscountedPrice", selectedProduct.discountedPrice)

            Log.d("SenderActivity", "productName: ${selectedProduct.name}")
            Log.d("SenderActivity", "productImage: ${selectedProduct.image}")


            startActivity(intent)
        }
        binding.rvSimilarProducts.adapter = productAdapter

        // Expand and truncate product description
        binding.btnShowMore.setOnClickListener {
            val tvDescription = binding.tvProductDesc

            if (tvDescription.maxLines == Integer.MAX_VALUE) {
                tvDescription.maxLines = 2
                tvDescription.ellipsize = TextUtils.TruncateAt.END
                binding.btnShowMore.text = "Selengkapnya"
            } else {
                tvDescription.maxLines = Integer.MAX_VALUE
                tvDescription.ellipsize = null
                binding.btnShowMore.text = "Lebih Sedikit"
            }
        }


        // Create the item decoration with desired space (in pixels)
        val startSpacingDecoration = StartSpacingItemDecoration(this, 12)
        binding.rvSimilarProducts.addItemDecoration(startSpacingDecoration)






        /*------------------- Trial Fragments -----------------------*/
        binding.btnBack.setOnClickListener{
            val destination = Intent(this, NavigationActivity::class.java)
            startActivity(destination)
        }

        binding.btnLike.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://x.com")
            startActivity(intent)
        }

        binding.btnShare.setOnClickListener {
            val destination = Intent(this, ShareProductActivity::class.java)
            startActivity(destination)
        }

        binding.btnCart.setOnClickListener {
            val bottomSheetFragment = BottomsheetFragment()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }

    }
}
