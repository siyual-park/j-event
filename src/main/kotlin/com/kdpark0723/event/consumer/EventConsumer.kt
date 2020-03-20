package com.kdpark0723.event.consumer

import com.kdpark0723.event.channel.CanReceiveEventChannel
import com.kdpark0723.event.event.Event
import com.kdpark0723.event.subscriber.EventSubscriber

class EventConsumer(
    private val channel: CanReceiveEventChannel,
    private val subscriber: EventSubscriber
) : EventSubscriber {
    override fun onEvent(event: Event) {
        subscriber.onEvent(event)
    }

    @Synchronized
    fun consume() {
        while (true) {
            val event = channel.receive() ?: break
            subscriber.onEvent(event)
        }
    }
}