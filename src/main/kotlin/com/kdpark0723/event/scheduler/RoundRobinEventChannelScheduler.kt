package com.kdpark0723.event.scheduler

import com.kdpark0723.event.channel.EventChannel
import com.kdpark0723.event.request.EventRequest

class RoundRobinEventChannelScheduler(override val channels: List<EventChannel>) : EventChannelScheduler(channels) {
    private var next: Int = -1

    override fun push(request: EventRequest) {
        if (channels.isNotEmpty()) {
            next = (next + 1) % channels.size
            channels[next].send(request)
        }
    }
}