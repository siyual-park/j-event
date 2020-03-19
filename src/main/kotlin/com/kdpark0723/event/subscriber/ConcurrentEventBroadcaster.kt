package com.kdpark0723.event.subscriber

import com.kdpark0723.event.event.Event
import java.util.*
import java.util.concurrent.ExecutorService

class ConcurrentEventBroadcaster(private val executorService: ExecutorService) : EventBroadcaster {
    private val subscribers: MutableSet<EventSubscriber> = Collections.synchronizedSet(mutableSetOf())

    override fun subscribe(subscriber: EventSubscriber) = subscribers.add(subscriber)
    override fun unsubscribe(subscriber: EventSubscriber) = subscribers.remove(subscriber)

    override fun onEvent(event: Event) {
        if (!executorService.isShutdown) {
            subscribers.forEach { executorService.submit { it.onEvent(event) } }
        }
    }
}