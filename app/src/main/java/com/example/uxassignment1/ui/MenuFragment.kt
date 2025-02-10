package com.example.uxassignment1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.uxassignment1.R
import com.example.uxassignment1.databinding.FragmentMenuBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ViewPagerAdapter(requireActivity())
        binding.viewPager.adapter = adapter
        binding.viewPager.isUserInputEnabled = true //swipable

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Tab 1"
                    tab.icon = context?.getDrawable(R.drawable.plus_svg_icon)
                }

                1 -> {
                    tab.text = "Tab 2"
                    tab.icon = context?.getDrawable(R.drawable.plus_svg_icon)
                }
            }
        }.attach()
    }
}