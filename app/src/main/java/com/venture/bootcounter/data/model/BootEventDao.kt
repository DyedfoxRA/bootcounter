package com.venture.bootcounter.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.Date

@Dao
interface BootEventDao {
    @Insert
    suspend fun insert(event: BootEvent)

    @Query("SELECT * FROM boot_events ORDER BY timestamp DESC")
    suspend fun getAllEvents(): List<BootEvent>

    @Query("SELECT COUNT(*) FROM boot_events WHERE DATE(timestamp / 1000, 'unixepoch') = DATE(:date / 1000, 'unixepoch')")
    suspend fun getEventsCountByDate(date: Date): Int

    @Query("SELECT * FROM boot_events ORDER BY timestamp DESC LIMIT 2")
    suspend fun getLastTwoEvents(): List<BootEvent>
}
