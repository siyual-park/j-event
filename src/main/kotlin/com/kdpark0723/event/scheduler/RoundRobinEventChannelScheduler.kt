package com.kdpark0723.event.scheduler

import com.kdpark0723.event.channel.EventChannel
import com.kdpark0723.event.error.ChannelIsClose
import com.kdpark0723.event.observer.EventObserver
import com.kdpark0723.event.request.EventRequest

class RoundRobinEventChannelScheduler(override val channels: List<EventChannel>) : EventChannelScheduler(channels) {
    private var next: Int = -1
    private var isClose = false

    override fun addReceiver(receiver: EventObserver): Boolean {
        return channels.all { it.addReceiver(receiver) }
    }

    override fun removeReceiver(receiver: EventObserver): Boolean {
        return channels.all { it.removeReceiver(receiver) }
    }

    override fun resolve() {
        if (isClose)
            throw ChannelIsClose()

        channels.forEach { it.resolve() }
    }

    override fun send(request: EventRequest) {
        if (isClose)
            throw ChannelIsClose()

        if (channels.isNotEmpty()) {
            next = ((next + 1) + channels.size) % channels.size
            channels[next].send(request)
        }
    }

    override fun receive(): EventRequest? {
        if (isClose)
            throw ChannelIsClose()

        next = ((next - 1) + channels.size) % channels.size

        return channels[next].receive()
    }

    override fun close() {
        isClose = true
    }
}