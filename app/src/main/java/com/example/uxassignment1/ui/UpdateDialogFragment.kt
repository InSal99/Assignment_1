package com.example.uxassignment1.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.uxassignment1.R
import com.example.uxassignment1.databinding.FragmentDialogBinding
import com.example.uxassignment1.databinding.FragmentUpdateDialogBinding

class UpdateDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentUpdateDialogBinding
    private var dialogListener: DialogListener? = null

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

        binding.btnSubmit.setOnClickListener {
            dialogListener?.onSubmit("Data " + binding.edtNameInput.editText?.text.toString() + " updated!")
            dialog?.dismiss()

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val fragment = parentFragment
        if(fragment is PeopleFragment) {
//            this.dialogListener = fragment.dialogListener
        }
    }

    interface DialogListener {
        fun onSubmit(text: String)
    }
}