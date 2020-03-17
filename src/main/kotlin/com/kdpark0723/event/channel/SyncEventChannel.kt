package com.kdpark0723.event.channel

import com.kdpark0723.event.queue.EventQueue
import com.kdpark0723.event.subscriber.EventSubscriber

class SyncEventChannel(queue: EventQueue) : EventChannel(queue) {
    private val subscribers: MutableSet<EventSubscriber> = mutableSetOf()

    override fun addSubscriber(subscriber: EventSubscriber): Boolean {
        return subscribers.add(subscriber)
    }

    override fun removeSubscriber(subscriber: EventSubscriber): Boolean {
        return subscribers.remove(subscriber)
    }

    override fun resolve() {
        while (queue.isNotEmpty()) {
            val request = queue.poll()

            subscribers.forEach { it.listener(request) }
        }
    }
}