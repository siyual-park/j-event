package com.kdpark0723.event.multiplexer

import com.kdpark0723.event.request.EventRequest
import com.kdpark0723.event.subscriber.EventSubscriber
import com.kdpark0723.event.subscriber.EventWorker

abstract class EventMultiplexer : EventSubscriber {
    abstract fun addWorker(worker: EventWorker): Boolean

    abstract fun removeWorker(worker: EventWorker): Boolean

    abstract fun removeWorker(event: String): Boolean

    abstract fun getWorker(event: String): EventWorker?

    override fun listener(request: EventRequest): Boolean {
        val worker = getWorker(request.name)
        return worker?.listener(request) ?: false
    }
}