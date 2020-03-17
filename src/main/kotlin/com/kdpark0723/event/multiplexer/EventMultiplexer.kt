package com.kdpark0723.event.multiplexer

import com.kdpark0723.event.observer.EventObserver
import com.kdpark0723.event.observer.EventWorker
import com.kdpark0723.event.request.EventRequest

abstract class EventMultiplexer : EventObserver {
    abstract fun addWorker(worker: EventWorker): Boolean

    abstract fun removeWorker(worker: EventWorker): Boolean

    abstract fun removeWorker(event: String): Boolean

    abstract fun getWorker(event: String): EventWorker?

    override fun listener(request: EventRequest) {
        getWorker(request.name)?.listener(request)
    }
}