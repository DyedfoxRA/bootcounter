package com.venture.bootcounter.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BootEventDao {

    @Insert
    fun insert(bootEvent: BootEvent)

    @Query("SELECT * FROM boot_event")
    fun getAllEvents(): List<BootEvent>

    @Query("SELECT COUNT(*) FROM boot_event WHERE date(timestamp) = date(:date)")
    fun getEventsCountByDate(date: String): Int

    @Query("SELECT * FROM boot_event ORDER BY timestamp DESC LIMIT 2")
    fun getLastTwoEvents(): List<BootEvent>
}