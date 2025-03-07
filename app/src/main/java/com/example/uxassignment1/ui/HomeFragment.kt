package com.example.uxassignment1.ui

//import android.R
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.uxassignment1.R
import com.example.uxassignment1.databinding.FragmentHomeBinding
import com.google.android.material.imageview.ShapeableImageView


class HomeFragment : Fragment() {
//    companion object{
//        var EXTRA_VALUE = "extra_value"
//    }

    private  lateinit var binding: FragmentHomeBinding

//    private  lateinit var productName: String
//    private  var productImage: Int = R.drawable.pentol
//    private  lateinit var productPrice: String
//    private  lateinit var productDiscount: String
//    private  lateinit var productDiscountedPrice: String

    private  var productName: String = "Product 1"
    private  var productImage: Int = R.drawable.pentol
    private  var productPrice: String = "Rp000"
    private  var productDiscount: String = "-%"
    private  var productDiscountedPrice: String = "Rp000"

    fun handleBackPress() {
        parentFragmentManager.popBackStack()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments?.let {

//            // Retrieve the transition name from the arguments
//            val transitionName = arguments?.getString("transitionName")
//            val imageView = requireView().findViewById<ShapeableImageView>(R.id.ivImageProduct)
//
//            // Set the transition name on the shared element
//            if (transitionName != null) {
//                imageView.transitionName = transitionName
//            }

            productName = it.getString("productName", "Product 000")
            productImage = it.getInt("productImage", R.drawable.pentol)
            productPrice = it.getString("productPrice", "Rp000")
            productDiscount = it.getString("productDiscount", "-%")
            productDiscountedPrice = it.getString("productDiscountedPrice", "Rp000")

            Log.d("HomeFragment", "productName: $productName")
            Log.d("HomeFragment", "productImage: $productImage")
            Log.d("HomeFragment", "productPrice: $productPrice")
            Log.d("HomeFragment", "productDiscount: $productDiscount")
            Log.d("HomeFragment", "productDiscountedPrice: $productDiscountedPrice")

        }
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.tvProductName.text = productName
//        binding.ivImageProduct.setImageResource(productImage)
        if (productImage != null) {
            binding.ivImageProduct.setImageResource(productImage)
        }
        binding.tvProductPrice.text = productPrice
        binding.cProductDiscount.text = productDiscount
        binding.tvDiscountedPrice.text = productDiscountedPrice
        binding.tvDiscountedPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        // Ensure the shared element transition is properly handled
//        val imageView: ShapeableImageView = binding.ivImageProduct
//        if (arguments != null) {
//            val transitionName = requireArguments().getString("product_image_transition")
//            if (transitionName != null) {
//                imageView.transitionName = transitionName
//            }
//        }

        // Retrieve the transition name from the arguments
        val transitionName = arguments?.getString("transitionName")
        val imageView: ShapeableImageView = binding.ivImageProduct

        // Set the transition name on the shared element
        if (transitionName != null) {
            imageView.transitionName = transitionName
        }

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

        binding.btnBack.setOnClickListener {
            if (parentFragmentManager.backStackEntryCount > 0) {
                parentFragmentManager.popBackStack()
            } else {
                activity?.finish()
            }
        }

    }

    var dialogListener: DialogTestFragment.DialogListener = object : DialogTestFragment.DialogListener {
        override fun onSubmit(text: String) {
            Toast.makeText(requireActivity(), text, Toast.LENGTH_SHORT).show()
        }
    }

}