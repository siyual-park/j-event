package com.kdpark0723.event.scheduler

import com.kdpark0723.event.event.Event
import com.kdpark0723.event.subscriber.EventSubscriber
import java.util.concurrent.ExecutorService

class CompoundedEventScheduler(
    subscriber: EventSubscriber,
    private val executorService: ExecutorService
) : EventSubscriber {
    private val sequentialEventScheduler = SequentialEventScheduler(subscriber)
    private val concurrentEventScheduler = ConcurrentEventScheduler(subscriber, executorService)

    override fun onEvent(event: Event) {
        if (executorService.isShutdown) sequentialEventScheduler.onEvent(event)
        else concurrentEventScheduler.onEvent(event)
    }
}