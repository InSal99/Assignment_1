package com.example.uxassignment1.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.uxassignment1.data.local.dao.DesignTeamDAO
import com.example.uxassignment1.data.local.database.EDTSDatabase
import com.example.uxassignment1.data.local.entity.DesignTeam
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DesignTeamRepository(application: Application) {
    private val designTeamDAO: DesignTeamDAO?
    private val allDesignTeam: Flow<List<DesignTeam>>?

    init {
        val database = EDTSDatabase.getInstance(application)
        designTeamDAO = database?.designTeamDao()
        allDesignTeam = designTeamDAO?.getAllTeam()
    }

    suspend fun insert(designTeam: DesignTeam) {
        withContext (Dispatchers.IO) {
            designTeamDAO?.insertTeam(designTeam)
        }
    }

    suspend fun update(designTeam: DesignTeam) {
        withContext(Dispatchers.IO) {
            designTeamDAO?.updateTeam(designTeam)
        }
    }

    suspend fun delete(designTeam: DesignTeam) {
        withContext(Dispatchers.IO) {
            designTeamDAO?.deleteTeam(designTeam)
        }
    }

    suspend fun deleteAllNotes() {
        withContext (Dispatchers.IO) {
            designTeamDAO?.deleteAlTeam()
        }
    }

    fun getAllDesignTeam(): Flow<List<DesignTeam>>? = allDesignTeam

}