package com.example.uxassignment1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.uxassignment1.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    companion object{
        var EXTRA_VALUE = "extra_value"
    }

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments!=null) {
            val label = arguments?.getString(EXTRA_VALUE)
            binding.tvTitle.text = label
        }

        binding.btnGoToHome.setOnClickListener {
            val fragmentHome = HomeFragment()
            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(R.id.navigation_container, fragmentHome, HomeFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
    }

}