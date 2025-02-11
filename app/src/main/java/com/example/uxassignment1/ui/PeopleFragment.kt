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

class PeopleFragment : Fragment(), UpdateDialogFragment.OnUpdateListener, AddDialogFragment.OnAddListener {

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
            showUpdateDialog(designTeam)

        }

    }

    private fun showUpdateDialog(designTeam: DesignTeam) {
        val dialogFragment = UpdateDialogFragment()
        dialogFragment.setDesignTeam(designTeam) // Pass the existing DesignTeam data
        dialogFragment.show(childFragmentManager, "UpdateDialog")
        Toast.makeText(requireContext(), "Updating Data", Toast.LENGTH_SHORT).show()
    }

    private fun showAddDialog() {
        val dialogFragment = AddDialogFragment()
        dialogFragment.show(childFragmentManager, "AddDesignTeamDialog")
    }

    override fun onAdd(designTeam: DesignTeam) {
        viewModel.insert(designTeam) // Call the insert method in ViewModel
        Toast.makeText(requireContext(), "${designTeam.name} added", Toast.LENGTH_SHORT).show()
    }

    override fun onUpdate(designTeam: DesignTeam) {
        viewModel.update(designTeam)
        Toast.makeText(requireContext(), "${designTeam.name} updated", Toast.LENGTH_SHORT).show()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAdd.setOnClickListener {
            showAddDialog()
        }

    }

}