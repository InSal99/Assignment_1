package com.example.uxassignment1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.uxassignment1.databinding.FragmentDialogBinding

class DialogFragment : DialogFragment() {
    private lateinit var binding: FragmentDialogBinding
    private var dialogListener:DialogListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubmit.setOnClickListener {
            dialogListener?.onSubmit("Hi, " + binding.edtNameInput.editText?.text.toString() + "!")
            dialog?.dismiss()

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val fragment = parentFragment
        if(fragment is HomeFragment) {
            this.dialogListener = fragment.dialogListener
        }
    }

    interface DialogListener {
        fun onSubmit(text: String)
    }
}