package com.venture.bootcounter.feature

import BootEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.venture.bootcounter.data.repo.BootEventRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BootViewModel(private val repository: BootEventRepository) : ViewModel() {
    private val _bootEvents = MutableStateFlow<List<BootEvent>>(emptyList())
    val bootEvents: StateFlow<List<BootEvent>> = _bootEvents

    init {
        loadBootEvents()
    }

    private fun loadBootEvents() {
        viewModelScope.launch {
            repository.getAllEvents().collect {
                _bootEvents.value = it
            }
        }
    }

    fun insertBootEvent(bootEvent: BootEvent) {
        viewModelScope.launch {
            repository.insertEvent(bootEvent)
        }
    }
}
