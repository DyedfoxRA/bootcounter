package com.venture.bootcounter.framework

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.venture.bootcounter.data.repo.BootEventRepository
import com.venture.bootcounter.formatToString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.concurrent.TimeUnit

class NotificationReceiver : BroadcastReceiver(), KoinComponent {
    private val repository: BootEventRepository by inject()

    override fun onReceive(context: Context, intent: Intent) {
        CoroutineScope(Dispatchers.IO).launch {
            val bootEvents = repository.getLastTwoEvents().first()
            val notificationText = when (bootEvents.size) {
                0 -> "No boots detected"
                1 -> "The boot was detected = ${bootEvents[0].timestamp.formatToString()}"
                else -> {
                    val delta = bootEvents[0].timestamp.time - bootEvents[1].timestamp.time
                    "Last boots time delta = ${TimeUnit.MILLISECONDS.toMinutes(delta)} minutes"
                }
            }
            NotificationHelper.showNotification(context, "Boot Event", notificationText)
        }
    }
}
