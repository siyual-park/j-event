package com.kdpark0723.event.scheduler

import com.kdpark0723.event.event.Event
import com.kdpark0723.event.subscriber.EventSubscriber
import java.util.concurrent.ExecutorService

class ConcurrentEventScheduler(
    private val subscriber: EventSubscriber,
    private val executorService: ExecutorService
) : EventSubscriber {
    override fun onEvent(event: Event) {
        executorService.submit { subscriber.onEvent(event) }
    }
}