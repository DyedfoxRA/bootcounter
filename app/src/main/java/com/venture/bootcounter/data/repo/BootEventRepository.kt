package com.venture.bootcounter.data.repo

import com.venture.bootcounter.data.model.BootEvent
import com.venture.bootcounter.data.model.BootEventDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BootEventRepository(private val bootEventDao: BootEventDao) {
    fun getAllEvents(): Flow<List<BootEvent>> = flow {
        emit(bootEventDao.getAllEvents())
    }

    suspend fun insertEvent(bootEvent: BootEvent) {
        bootEventDao.insert(bootEvent)
    }

    fun getLastTwoEvents(): Flow<List<BootEvent>> = flow {
        emit(bootEventDao.getLastTwoEvents())
    }
}
