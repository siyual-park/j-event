package com.kdpark0723.event.distributor

import com.kdpark0723.event.channel.TransferableEventChannel
import com.kdpark0723.event.event.Event
import com.kdpark0723.event.publisher.EventPublisher
import com.kdpark0723.event.subscriber.EventSubscriber
import java.util.Collections.synchronizedSet

class EventDistributor(
    private val channel: TransferableEventChannel
) : EventSubscriber, EventPublisher {
    private val subscribers: MutableSet<EventSubscriber> = synchronizedSet(mutableSetOf())

    fun subscribe(subscriber: EventSubscriber) = subscribers.add(subscriber)
    fun unsubscribe(subscriber: EventSubscriber) = subscribers.remove(subscriber)

    fun emit(event: Event) {
        publishEvent(event)
    }

    fun distribute() {
        while (channel.isNotEmpty()) {
            onEvent(channel.receive())
        }
    }

    override fun publishEvent(event: Event) {
        channel.send(event)
    }

    override fun onEvent(event: Event) {
        subscribers.forEach { it.onEvent(event) }
    }
}