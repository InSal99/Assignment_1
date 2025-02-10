package com.example.uxassignment1.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.uxassignment1.R
import com.example.uxassignment1.databinding.ActivityMainBinding
import com.example.uxassignment1.databinding.ActivityShareProductBinding
import com.example.uxassignment1.databinding.ProductCardBinding
import kotlin.math.abs

class ShareProductActivity : AppCompatActivity() {
    private lateinit var shareProductAdapter: ShareProductAdapter
    private lateinit var products: MutableList<Product>
    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var binding: ActivityShareProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityShareProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        setUpTransformer()

        viewPager2.registerOnPageChangeCallback(object  : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
//                handler.postDelayed(runnable, 2000)
            }
        })

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
//        handler.postDelayed(runnable, 2000)
    }

    private val runnable = Runnable {
//        viewPager2.currentItem = viewPager2.currentItem + 1
        val nextItem = (viewPager2.currentItem + 1) % products.size
        viewPager2.currentItem = nextItem
    }

    private fun setUpTransformer() {
//        val transformer = CompositePageTransformer()
//        transformer.addTransformer(MarginPageTransformer(40))  // Add margin between pages
//        transformer.addTransformer { page, position ->
//            val r = 1 - abs(position)  // Scale based on position
//            page.scaleY = (0.85 + r + 0.14F).toFloat()
//        }
//        viewPager2.setPageTransformer(transformer)
    }

    private fun init() {
        viewPager2 = binding.vpgProduct
        handler = Handler(Looper.myLooper()!!)
        products = mutableListOf(
            Product("Dark Hojicha Sweet Potato Parfait", R.drawable.product_1, "Rp89.000", "0%", "Rp89.000"),
            Product("Very Berry Pavlovas", R.drawable.product_2, "Rp55.000", "7%", "Rp59.000"),
            Product("Egg Scones", R.drawable.product_3, "Rp138.500", "10%", "Rp216.000"),
            Product("Autumn Fatcarons", R.drawable.product_4, "Rp285.000", "5%", "Rp299.000"),
            Product("Poured Matcha Tiramisu", R.drawable.product_current, "Rp77.000", "10%", "Rp85.000")
        )
        shareProductAdapter = ShareProductAdapter(products, viewPager2) { selectedProduct ->
//            val intent = Intent(this, NavigationActivity::class.java)
            intent.putExtra("productName", selectedProduct.name)
            intent.putExtra("productImage", selectedProduct.image)
            intent.putExtra("productPrice", selectedProduct.price)
            intent.putExtra("productDiscount", selectedProduct.discount)
            intent.putExtra("productDiscountedPrice", selectedProduct.discountedPrice)

            val uri = intent.data

            if (uri != null) {
                Log.d("Tes Uri", "Uri Passed $uri")
            }

            Log.d("Tes Uri", "Uri Passed $uri")

            val sharedIntent = Intent (Intent.ACTION_SEND)

            sharedIntent.type = "text/plain"
            sharedIntent.putExtra(Intent.EXTRA_TEXT, "Share ${selectedProduct.name}")
            startActivity(Intent.createChooser(sharedIntent, "Share Via"))
        }

        viewPager2.adapter = shareProductAdapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }
}