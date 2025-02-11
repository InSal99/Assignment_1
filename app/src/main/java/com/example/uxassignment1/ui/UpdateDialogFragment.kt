package com.example.uxassignment1.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.uxassignment1.R
import com.example.uxassignment1.data.local.entity.DesignTeam
import com.example.uxassignment1.databinding.FragmentDialogBinding
import com.example.uxassignment1.databinding.FragmentUpdateDialogBinding

class UpdateDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentUpdateDialogBinding
    private var listener: OnUpdateListener? = null
    private lateinit var designTeam: DesignTeam

    interface OnUpdateListener {
        fun onUpdate(designTeam: DesignTeam)
    }

    // Use this method to pass the existing DesignTeam data
    fun setDesignTeam(designTeam: DesignTeam) {
        this.designTeam = designTeam
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Pre-fill the input fields with existing data
        binding.edtUpdateNameInput.editText?.setText(designTeam.name)
        binding.edtUpdateRoleInput.editText?.setText(designTeam.division)

        binding.btnSubmitUpdate.setOnClickListener {
            val updatedName = binding.edtUpdateNameInput.editText?.text.toString()
            val updatedDivision = binding.edtUpdateRoleInput.editText?.text.toString()

            if (updatedName.isNotEmpty() && updatedDivision.isNotEmpty()) {
                // Create a new DesignTeam object with updated values
                val updatedDesignTeam = designTeam.copy(name = updatedName, division = updatedDivision)
                listener?.onUpdate(updatedDesignTeam) // Notify the listener
                dialog?.dismiss()
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val fragment = parentFragment
        if (fragment is PeopleFragment) {
            this.listener = fragment
        }
    }

    interface DialogListener {
        fun onSubmit(text: String)
    }
}