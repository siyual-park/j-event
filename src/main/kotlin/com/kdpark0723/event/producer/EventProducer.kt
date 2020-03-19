package com.kdpark0723.event.producer

import com.kdpark0723.event.channel.CanSendEventChannel
import com.kdpark0723.event.event.Event
import com.kdpark0723.event.publisher.EventPublisher

class EventProducer(
    private val channel: CanSendEventChannel
) : EventPublisher {
    override fun publishEvent(event: Event) {
        channel.send(event)
    }
}