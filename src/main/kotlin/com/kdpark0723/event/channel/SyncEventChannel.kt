package com.kdpark0723.event.channel

import com.kdpark0723.event.error.ChannelIsClose
import com.kdpark0723.event.observer.EventObserver
import com.kdpark0723.event.queue.EventQueue
import com.kdpark0723.event.request.EventRequest

class SyncEventChannel(private var queue: EventQueue) : EventChannel {
    private val subscribers: MutableSet<EventObserver> = mutableSetOf()
    private var isClose = false

    override fun addReceiver(receiver: EventObserver): Boolean {
        return subscribers.add(receiver)
    }

    override fun removeReceiver(receiver: EventObserver): Boolean {
        return subscribers.remove(receiver)
    }

    override fun send(request: EventRequest) {
        if (isClose)
            throw ChannelIsClose()

        queue.add(request)
    }

    override fun resolve() {
        if (isClose)
            throw ChannelIsClose()

        while (isNotEmpty()) {
            receive()
        }
    }

    override fun receive() {
        if (isClose)
            throw ChannelIsClose()

        if (isNotEmpty()) {
            val request = queue.poll()

            subscribers.forEach { it.listener(request) }
        }
    }

    override fun close() {
        isClose = true
    }

    override fun isNotEmpty(): Boolean {
        return queue.isNotEmpty()
    }

    override fun isEmpty(): Boolean {
        return queue.isEmpty()
    }
}