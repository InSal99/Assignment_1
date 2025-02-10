package com.example.uxassignment1.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.uxassignment1.R
import com.example.uxassignment1.data.local.entity.DesignTeam
import com.example.uxassignment1.databinding.FragmentPeopleBinding
import com.example.uxassignment1.viewmodel.DesignTeamViewModel
import com.example.uxassignment1.viewmodel.DesignTeamViewModelFactory

class PeopleFragment : Fragment() {

    private lateinit var binding: FragmentPeopleBinding
    private lateinit var viewModel: DesignTeamViewModel
    private lateinit var designTeam: List<DesignTeam>
    private lateinit var designTeamAdapter: DesignTeamAdapter

    private fun initInsertDesignTeam() {
        designTeam = listOf(
            DesignTeam(
                name = "Rizka Chinna",
                division = "UX Engineer"
            ),
            DesignTeam(
                name = "Shidig Bagus",
                division = "UX Engineer"
            ),
            DesignTeam(
                name = "Fika ARkiliani",
                division = "Associate Product Design"
            ),
            DesignTeam(
                name = "Rymarshanda",
                division = "Associate Product Design"
            ),
            DesignTeam(
                name = "Dzaky Waly",
                division = "Associate Product Design"
            ),
            DesignTeam(
                name = "Anastania",
                division = "Product Design Manager"
            ),
            DesignTeam(
                name = "Fares Farhan",
                division = "SVP Product Design"
            )
        )
        designTeam.forEach {
            viewModel.insert(it)
        }
    }

    private fun initAdapter() {
        binding.rvDesignTeam.adapter = designTeamAdapter

        designTeamAdapter.setOnDeleteClickListener { designTeam ->
            viewModel.delete(designTeam)
            Toast.makeText(requireContext(), "${designTeam.name} deleted", Toast.LENGTH_SHORT).show()
        }

        designTeamAdapter.setOnUpdateClickListener { designTeam ->
//            showUpdate dialog(designTeam)
            Toast.makeText(requireContext(), "${designTeam.name} deleted", Toast.LENGTH_SHORT).show()
        }

    }



    private fun initViewModel() {
        viewModel.getDesignTeam()
    }

    private fun initDesignTeam() {
        viewModel.getDesignTeam.observe(viewLifecycleOwner) { result ->
            Log.d("PeopleFragment", "Design Team List Size: ${result.size}")
            designTeamAdapter.differ.submitList(result)
        }
    }

    private fun observeDesignTeams() {
        viewModel.getDesignTeam.observe(viewLifecycleOwner) { designTeams ->
            designTeamAdapter.differ.submitList(designTeams)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPeopleBinding.inflate(inflater, container, false)

        val factory = DesignTeamViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, factory)[DesignTeamViewModel::class.java]
        designTeamAdapter = DesignTeamAdapter()

//        initInsertDesignTeam()
        initAdapter()
        initViewModel()
        initDesignTeam()
        observeDesignTeams()


        return binding.root
    }

    var dialogListener: DialogFragment.DialogListener = object : DialogFragment.DialogListener {
        override fun onSubmit(text: String) {
            Toast.makeText(requireActivity(), text, Toast.LENGTH_SHORT).show()
        }
    }

}