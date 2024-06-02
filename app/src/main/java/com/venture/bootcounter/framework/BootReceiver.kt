package com.venture.bootcounter.framework

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.venture.bootcounter.data.model.BootEvent
import com.venture.bootcounter.data.repo.BootEventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.Date

class BootReceiver : BroadcastReceiver(), KoinComponent {
    private val repository: BootEventRepository by inject()

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            Toast.makeText(context, "Boot Completed", Toast.LENGTH_SHORT).show()
            val bootEvent = BootEvent(timestamp = Date().time)

            CoroutineScope(Dispatchers.IO).launch {
                repository.insertEvent(bootEvent)
            }

            NotificationScheduler.scheduleRepeatingNotifications(context)
        }
    }
}
