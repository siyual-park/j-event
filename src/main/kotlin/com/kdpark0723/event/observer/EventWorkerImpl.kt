package com.kdpark0723.event.observer

import com.kdpark0723.event.request.EventRequest

class EventWorkerImpl(event: String, private val worker: (request: EventRequest) -> Unit) : EventWorker(event) {
    override fun listener(request: EventRequest) = worker(request)
}