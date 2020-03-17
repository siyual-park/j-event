package com.kdpark0723.event.distributor

import com.kdpark0723.event.channel.TransferableEventChannel
import com.kdpark0723.event.event.Event
import com.kdpark0723.event.publisher.EventPublisher
import com.kdpark0723.event.subscriber.EventSubscriber
import java.util.Collections.synchronizedSet

abstract class EventDistributor(
    protected val channel: TransferableEventChannel
) : EventSubscriber, EventPublisher {
    protected val subscribers: MutableSet<EventSubscriber> = synchronizedSet(mutableSetOf())

    fun subscribe(subscriber: EventSubscriber) = subscribers.add(subscriber)
    fun unsubscribe(subscriber: EventSubscriber) = subscribers.remove(subscriber)

    fun emit(event: Event) {
        publishEvent(event)
    }

    abstract fun distribute()

    override fun publishEvent(event: Event) {
        channel.send(event)
    }
}