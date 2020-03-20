package com.kdpark0723.event.channel

import com.kdpark0723.event.event.Event
import com.kdpark0723.event.queue.EventQueue

class TransferableEventQueueChannel(
    private val queue: EventQueue
) : TransferableEventChannel {
    private var isClose = false

    override fun send(event: Event) {
        queue.add(event)
    }

    override fun receive(): Event? {
        return queue.poll()
    }

    override fun isClose(): Boolean {
        return isClose
    }

    override fun close() {
        isClose = true
    }

    override fun isEmpty(): Boolean {
        return queue.isEmpty()
    }

    override fun isNotEmpty(): Boolean {
        return queue.isNotEmpty()
    }
}