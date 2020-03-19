package com.kdpark0723.event.subscriber

import com.kdpark0723.event.event.Event
import java.util.*

class SingleEventBroadcaster : EventBroadcaster {
    private val subscribers: MutableSet<EventSubscriber> = Collections.synchronizedSet(mutableSetOf())

    override fun subscribe(subscriber: EventSubscriber) = subscribers.add(subscriber)
    override fun unsubscribe(subscriber: EventSubscriber) = subscribers.remove(subscriber)

    override fun onEvent(event: Event) {
        subscribers.forEach { it.onEvent(event) }
    }
}