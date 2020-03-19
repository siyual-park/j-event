package com.kdpark0723.event.subscriber

import com.kdpark0723.event.event.Event
import com.kdpark0723.event.event.NamedSource
import java.util.concurrent.ExecutorService

const val executorServiceShutdownEvent = "EXECUTOR_SERVICE_SHUTDOWN_EVENT"

class ExecutorServiceShutdownEventSubscriber : EventSubscriber {
    override fun onEvent(event: Event) {
        convertEvent(event)?.run { shutdown() }
    }

    private fun convertEvent(event: Event): ExecutorService? {
        val source = event.source
        if (source is NamedSource) {
            if (source.name == executorServiceShutdownEvent) {
                if (source.source is ExecutorService) {
                    return source.source
                }
            }
        }

        return null
    }
}