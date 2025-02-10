package com.example.uxassignment1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.uxassignment1.data.local.entity.DesignTeam
import com.example.uxassignment1.data.repository.DesignTeamRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DesignTeamViewModel(application: Application): AndroidViewModel(application) {

    private val repository: DesignTeamRepository = DesignTeamRepository(application)
    private var _getDesignTeam: MutableLiveData<List<DesignTeam>> = MutableLiveData()
    val getDesignTeam: LiveData<List<DesignTeam>> get() = _getDesignTeam

    fun insert(designTeam: DesignTeam) = viewModelScope.launch {
        repository.insert(designTeam)
    }

    fun update(designTeam: DesignTeam) = viewModelScope.launch {
        repository. update(designTeam)
    }

    fun delete(designTeam: DesignTeam) = viewModelScope.launch {
        repository.delete(designTeam)
    }

    fun deleteAllDesignTeam() = viewModelScope.launch {
        repository.deleteAllNotes()
    }

    fun getDesignTeam() = repository.getAllDesignTeam()
        ?.onEach { result ->
            _getDesignTeam.value = result
        }?.launchIn(viewModelScope)
}