package com.example.uxassignment1.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "design_team")
data class DesignTeam(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val division: String = ""
)
