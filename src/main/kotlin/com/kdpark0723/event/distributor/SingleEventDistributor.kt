package com.kdpark0723.event.distributor

import com.kdpark0723.event.channel.TransferableEventChannel
import com.kdpark0723.event.event.Event

class SingleEventDistributor(
    channel: TransferableEventChannel
) : EventDistributor(channel) {
    override fun distribute() {
        while (channel.isNotEmpty()) {
            onEvent(channel.receive())
        }
    }

    override fun onEvent(event: Event) {
        subscribers.forEach { it.onEvent(event) }
    }
}