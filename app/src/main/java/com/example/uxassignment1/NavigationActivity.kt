package com.example.uxassignment1

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

        val productName = intent.getStringExtra("productName")
        val productImage = intent.getIntExtra("productImage", R.drawable.pentol)
        val productPrice = intent.getStringExtra("productPrice")
        val productDiscount = intent.getStringExtra("productDiscount")
        val productDiscountedPrice = intent.getStringExtra("productDiscountedPrice")
        Log.d("image res1", productImage.toString())

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
        fragment1.arguments = bundle

        val fragment = mFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)
        if(fragment !is HomeFragment) {
            mFragmentManager
                .beginTransaction()
                .add(R.id.navigation_container, fragment1, HomeFragment::class.java.simpleName)
                .commit()
        }
//
//        binding.btnBack.setOnClickListener {
//            onBackPressed()
//        }


    }
}