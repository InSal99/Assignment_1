package com.example.uxassignment1.ui

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.uxassignment1.R
import com.example.uxassignment1.databinding.ActivityNavigationBinding

class NavigationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        // Retrieve the transition name from the intent
        val transitionName = intent.getStringExtra("transitionName")

        val productName = intent.getStringExtra("productName")
        val productImage = intent.getIntExtra("productImage", R.drawable.pentol)
        val productPrice = intent.getStringExtra("productPrice")
        val productDiscount = intent.getStringExtra("productDiscount")
        val productDiscountedPrice = intent.getStringExtra("productDiscountedPrice")

        Log.d("ReceiverActivity", "productName: $productName")
        Log.d("ReceiverActivity", "productImage: $productImage")
        Log.d("ReceiverActivity", "productPrice: $productPrice")
        Log.d("ReceiverActivity", "productDiscount: $productDiscount")
        Log.d("ReceiverActivity", "productDiscountedPrice: $productDiscountedPrice")


//        binding.tvProductName.text = productName
//        if (productImage != null) {
//            binding.ivImageProduct.setImageResource(productImage.toInt())
//        }
//        binding.tvProductPrice.text = productPrice
//        binding.cProductDiscount.text = productDiscount
//        binding.tvDiscountedPrice.text = productDiscountedPrice

        val mFragmentManager = super.getSupportFragmentManager()
        val fragment1  = HomeFragment()
        val bundle = Bundle()
        bundle.putString("productName", productName)
        bundle.putInt("productImage", productImage)
        Log.d("image resss", productImage.toString())
        bundle.putString("productPrice", productPrice)
        bundle.putString("productDiscount", productDiscount)
        bundle.putString("productDiscountedPrice", productDiscountedPrice)
        bundle.putString("transitionName", transitionName) // Pass the transition name
        Log.d("ReceiverActivity", "Passing to fragment productName: $productName")
        Log.d("ReceiverActivity", "Passing to fragment productImage: $productImage")
        Log.d("ReceiverActivity", "Passing to fragment transitionName: $transitionName")

        fragment1.arguments = bundle

        val fragment = mFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)

        if (fragment == null) {
            mFragmentManager
                .beginTransaction()
                .replace(R.id.navigation_container, fragment1, HomeFragment::class.java.simpleName)
                .commit()
        }

//        binding.btnBack.setOnClickListener {
//            onBackPressed()
//        }
    }
}
