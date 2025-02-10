package com.example.uxassignment1.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.uxassignment1.data.local.entity.DesignTeam
import kotlinx.coroutines.flow.Flow

@Dao
interface DesignTeamDAO {

    @Insert
    fun insertTeam(designTeam: DesignTeam)

    @Update
    fun updateTeam(designTeam: DesignTeam)

    @Delete
    fun deleteTeam(designTeam: DesignTeam)

    @Query ("DELETE FROM design_team")
    fun deleteAlTeam()

    @Query("SELECT * FROM design_team")
    fun getAllTeam(): Flow<List<DesignTeam>>

}