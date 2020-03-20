package com.kdpark0723.event.scheduler

import com.kdpark0723.event.event.Event
import com.kdpark0723.event.subscriber.EventSubscriber

class SequentialEventScheduler(private val subscriber: EventSubscriber) : EventSubscriber {
    override fun onEvent(event: Event) {
        subscriber.onEvent(event)
    }
}