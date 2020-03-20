package com.kdpark0723.event.broadcaster

import com.kdpark0723.event.event.Event
import com.kdpark0723.event.subscriber.EventSubscriber

class SequentialEventBroadcaster : EventBroadcaster {
    private val subscribers: MutableSet<EventSubscriber> = mutableSetOf()

    override fun subscribe(subscriber: EventSubscriber) = subscribers.add(subscriber)
    override fun unsubscribe(subscriber: EventSubscriber) = subscribers.remove(subscriber)

    override fun onEvent(event: Event) {
        subscribers.forEach { it.onEvent(event) }
    }
}