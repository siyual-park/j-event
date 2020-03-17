package com.kdpark0723.event.channel

import com.kdpark0723.event.queue.EventQueue
import com.kdpark0723.event.request.EventRequest
import com.kdpark0723.event.subscriber.EventSubscriber

abstract class EventChannel(
    protected val queue: EventQueue
) {
    abstract fun addSubscriber(subscriber: EventSubscriber): Boolean

    abstract fun removeSubscriber(subscriber: EventSubscriber): Boolean

    abstract fun resolve()

    fun send(request: EventRequest) {
        queue.add(request)
    }
}