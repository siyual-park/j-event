package com.kdpark0723.event.distributor

import com.kdpark0723.event.channel.TransferableEventChannel
import com.kdpark0723.event.subscriber.EventBroadcaster
import com.kdpark0723.event.subscriber.EventSubscriber

class EventBroadcastDistributor(
    channel: TransferableEventChannel,
    private val broadcaster: EventBroadcaster
) : EventDistributor(channel, broadcaster), EventBroadcaster {

    override fun subscribe(subscriber: EventSubscriber) = broadcaster.subscribe(subscriber)
    override fun unsubscribe(subscriber: EventSubscriber) = broadcaster.unsubscribe(subscriber)
}