package com.example.uxassignment1

import android.content.Context
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var serviceRecyclerView: RecyclerView
    private lateinit var serviceAdapter: ServiceAdapter
    private lateinit var services: List<Service>
    private lateinit var productRecyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var products: List<Product>
    lateinit var productImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        services = listOf(
            Service("SERVICE CHARGE DINE IN", "+2.000", false),
            Service("Kirim Beku (Panaskan Sendiri)", "", false)
        )
        Log.d("MyActivity", "Services List: ${services.size}")

        products = listOf(
            Product("Product 1", R.drawable.product_1, "Rp10.000", "10%", "Rp9000"),
            Product("Product 2", R.drawable.product_2, "Rp10.000", "10%", "Rp9000"),
            Product("Product 3", R.drawable.product_3, "Rp10.000", "10%", "Rp9000"),
            Product("Product 4", R.drawable.product_4, "Rp10.000", "10%", "Rp9000"),
            Product("Product 5", R.drawable.product_current, "Rp10.000", "10%", "Rp9000")
//            Product("Product 1", R.drawable.product_1, "Rp10.000", "10%", "Rp9000"),
//            Product("Product 2", R.drawable.product_2, "Rp10.000", "10%", "Rp9000"),
//            Product("Product 3", R.drawable.product_3, "Rp10.000", "10%", "Rp9000"),
//            Product("Product 4", R.drawable.product_4, "Rp10.000", "10%", "Rp9000"),
//            Product("Product 5", R.drawable.product_current, "Rp10.000", "10%", "Rp9000")
        )
        Log.d("MyActivity", "Product List: ${products.size}")

        serviceRecyclerView = findViewById(R.id.rvServingList)
        serviceRecyclerView.layoutManager = LinearLayoutManager(this)
        serviceAdapter = ServiceAdapter(services) { selectedService ->
            // Handle selection: Uncheck all other items
            services.forEach { it.isChecked = false }
            selectedService.isChecked = true
            serviceAdapter.notifyDataSetChanged() // Notify adapter to refresh view
        }
        serviceRecyclerView.adapter = serviceAdapter

        productRecyclerView = findViewById(R.id.rvSimilarProducts)
        productRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        productAdapter = ProductAdapter(products)
        productRecyclerView.adapter = productAdapter


        val textview = findViewById<TextView>(R.id.tvDiscountedPrice)
        textview.setPaintFlags(textview.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)

        val recyclerView = findViewById<RecyclerView>(R.id.rvSimilarProducts)

        // Create the item decoration with desired space (in pixels)
        val startSpacingDecoration = StartSpacingItemDecoration(this, 12) // Space of 50px at the start
        recyclerView.addItemDecoration(startSpacingDecoration)



    }
}

class StartSpacingItemDecoration(context: Context, private val spaceInPx: Int) : RecyclerView.ItemDecoration() {

    private val space: Int

    init {
        // Convert dp to px using the display metrics
        val density = context.resources.displayMetrics.density
        space = (spaceInPx * density).toInt()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        // Add space only at the start (left/top)
        if (parent.getChildAdapterPosition(view) == 0) {
            if (parent.layoutManager is LinearLayoutManager) {
                val layoutManager = parent.layoutManager as LinearLayoutManager
                if (layoutManager.orientation == RecyclerView.HORIZONTAL) {
                    outRect.left = space // Add space on the left for horizontal orientation
                } else {
                    outRect.top = space // Add space at the top for vertical orientation
                }
            }
        }
    }
}