package com.kdpark0723.event.distributor

import com.kdpark0723.event.channel.TransferableEventChannel
import com.kdpark0723.event.consumer.EventConsumer
import com.kdpark0723.event.event.Event
import com.kdpark0723.event.producer.EventProducer
import com.kdpark0723.event.publisher.EventPublisher
import com.kdpark0723.event.subscriber.EventBroadcaster
import com.kdpark0723.event.subscriber.EventSubscriber

class EventDistributor(
    channel: TransferableEventChannel,
    private val broadcaster: EventBroadcaster
) : EventSubscriber, EventPublisher, EventBroadcaster {
    private val consumer = EventConsumer(channel, broadcaster)
    private val producer = EventProducer(channel)

    override fun subscribe(subscriber: EventSubscriber) = broadcaster.subscribe(subscriber)
    override fun unsubscribe(subscriber: EventSubscriber) = broadcaster.unsubscribe(subscriber)

    override fun publishEvent(event: Event) = producer.publishEvent(event)

    override fun onEvent(event: Event) = consumer.onEvent(event)

    fun distribute() = consumer.consume()
}