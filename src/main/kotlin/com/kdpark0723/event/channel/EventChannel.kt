package com.kdpark0723.event.channel

import com.kdpark0723.event.observer.EventObserver
import com.kdpark0723.event.request.EventRequest

interface EventChannel {
    fun addReceiver(receiver: EventObserver): Boolean

    fun removeReceiver(receiver: EventObserver): Boolean

    fun send(request: EventRequest)

    fun receive()

    fun resolve()

    fun close()

    fun isNotEmpty(): Boolean

    fun isEmpty(): Boolean
}