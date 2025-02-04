package com.example.uxassignment1

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.uxassignment1.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
//    companion object{
//        var EXTRA_VALUE = "extra_value"
//    }

    private  lateinit var binding: FragmentHomeBinding

    private  var productName: String = "Product 1"
    private  var productImage: Int = R.drawable.pentol
    private  var productPrice: String = "Rp000"
    private  var productDiscount: String = "-%"
    private  var productDiscountedPrice: String = "Rp000"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments?.let {
            productName = it.getString("productName", "Product 000")
            productImage = it.getInt("productImage", R.drawable.pentol)
            productPrice = it.getString("productPrice", "Rp000")
            productDiscount = it.getString("productDiscount", "-%")
            productDiscountedPrice = it.getString("productDiscountedPrice", "Rp000")
        }
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.tvProductName.text = productName
        binding.ivImageProduct.setImageResource(productImage)
        binding.tvProductPrice.text = productPrice
        binding.cProductDiscount.text = productDiscount
        binding.tvDiscountedPrice.text = productDiscountedPrice
        binding.tvDiscountedPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGoToProfile.setOnClickListener {
            val fragmentProfile = ProfileFragment()

            val profileBundle = Bundle()
            profileBundle.putString(ProfileFragment.EXTRA_VALUE, "Welcome to Profile Page!")
            fragmentProfile.arguments = profileBundle

            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(R.id.navigation_container, fragmentProfile, ProfileFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }

        binding.btnGoToDialog.setOnClickListener {
            val dialogFragment = DialogFragment()
            val mFragmentManager = childFragmentManager
            dialogFragment.show(mFragmentManager, DialogFragment::class.java.simpleName)
        }

    }

    private fun handleBackPress() {
        // Handle the back press logic here, like pop the back stack
        parentFragmentManager.popBackStack()
    }

    var dialogListener:DialogFragment.DialogListener = object : DialogFragment.DialogListener {
        override fun onSubmit(text: String) {
            Toast.makeText(requireActivity(), text, Toast.LENGTH_SHORT).show()
        }
    }

}