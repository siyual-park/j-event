package com.kdpark0723.event.distributor

import com.kdpark0723.event.channel.TransferableEventChannel
import com.kdpark0723.event.consumer.EventConsumer
import com.kdpark0723.event.event.Event
import com.kdpark0723.event.producer.EventProducer
import com.kdpark0723.event.publisher.EventPublisher
import com.kdpark0723.event.subscriber.EventSubscriber

open class EventDistributor(
    channel: TransferableEventChannel,
    subscriber: EventSubscriber
) : EventSubscriber, EventPublisher {
    private val consumer = EventConsumer(channel, subscriber)
    private val producer = EventProducer(channel)

    override fun publishEvent(event: Event) = producer.publishEvent(event)

    override fun onEvent(event: Event) = consumer.onEvent(event)

    fun distribute() = consumer.consume()
}