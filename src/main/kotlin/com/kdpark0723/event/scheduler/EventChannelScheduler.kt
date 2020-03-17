package com.kdpark0723.event.scheduler

import com.kdpark0723.event.channel.EventChannel
import com.kdpark0723.event.request.EventRequest
import com.kdpark0723.event.subscriber.EventSubscriber

abstract class EventChannelScheduler(
    protected open val channels: Collection<EventChannel>
) {
    abstract fun push(request: EventRequest)

    fun addSubscriberAll(subscriber: EventSubscriber): Boolean {
        return channels.all { it.addSubscriber(subscriber) }
    }

    fun resolveAll() {
        channels.forEach { it.resolve() }
    }
}