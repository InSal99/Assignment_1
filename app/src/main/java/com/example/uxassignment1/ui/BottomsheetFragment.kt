package com.example.uxassignment1.ui

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.uxassignment1.R
import com.example.uxassignment1.databinding.FragmentBottomsheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomsheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomsheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBottomsheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the bottom sheet view from the dialog (find the design_bottom_sheet view)
        val bottomSheet = dialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

        // Get BottomSheetBehavior from the bottom sheet
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet!!)
//        val modalBottomSheetBehavior = (bottomSheet.dialog as BottomSheetDialog).behavior
// Use this to programmatically apply behavior attributes

        // Make the bottom sheet draggable and set it to full-screen when expanded
        bottomSheetBehavior.isDraggable = true

        // Set the peek height to a small value, so it shows as collapsed initially
        val screenHeight = Resources.getSystem().displayMetrics.heightPixels
        bottomSheetBehavior.peekHeight = screenHeight / 2

        // Set the expanded height to 85% of the screen height
        val expandedHeight = (screenHeight * 0.95).toInt()
        bottomSheetBehavior.maxHeight = expandedHeight

        // You can set the state to fully expanded
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        // Optional: Make the bottom sheet hideable or not
        bottomSheetBehavior.isHideable = true

        // Add a callback to listen to state changes (expanded, collapsed, etc.)
        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(view: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        // Full screen
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        // Minimized (peek state)
//                        dismiss()
                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        // Bottom sheet is hidden
                        dismiss()
                    }
                }
            }

            override fun onSlide(view: View, slideOffset: Float) {
                // This method is called when the sheet is sliding
                Log.d("height", "${bottomSheetBehavior.state}")
            }
        })

        // Set up the dismiss button
        binding.dismissButton.setOnClickListener {
            dismiss() // Dismiss the bottom sheet when the button is clicked
        }
    }

    // Ensure to clean up the binding reference when the fragment is destroyed
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}