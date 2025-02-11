package com.example.uxassignment1.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.uxassignment1.data.local.entity.DesignTeam
import com.example.uxassignment1.databinding.FragmentAddDialogBinding

class AddDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentAddDialogBinding
    private var listener: OnAddListener? = null

    interface OnAddListener {
        fun onAdd(designTeam: DesignTeam)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubmitAdd.setOnClickListener {
            val name = binding.edtAddNameInput.editText?.text.toString()
            val division = binding.edtAddRoleInput.editText?.text.toString()

            if (name.isNotEmpty() && division.isNotEmpty()) {
                val newDesignTeam = DesignTeam(name = name, division = division)
                listener?.onAdd(newDesignTeam) // Notify the listener
                dialog?.dismiss()
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        val fragment = parentFragment
//        if (fragment is PeopleFragment) {
//            this.listener = fragment
//        }
//    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val fragment = parentFragment
        if (fragment is OnAddListener) {
            this.listener = fragment // Set the listener to the parent fragment
        } else {
            throw ClassCastException("$fragment must implement OnAddListener")
        }
    }
}