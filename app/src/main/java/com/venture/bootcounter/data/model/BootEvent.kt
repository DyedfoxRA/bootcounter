package com.venture.bootcounter.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "boot_events")
data class BootEvent(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timestamp: Date
)