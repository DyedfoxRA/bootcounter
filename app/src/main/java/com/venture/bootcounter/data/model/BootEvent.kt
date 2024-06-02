package com.venture.bootcounter.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "boot_event")
data class BootEvent(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timestamp: Long
)