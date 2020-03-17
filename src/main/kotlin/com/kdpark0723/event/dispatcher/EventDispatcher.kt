package com.kdpark0723.event.dispatcher

import com.kdpark0723.event.multiplexer.EventMultiplexer
import com.kdpark0723.event.request.EventRequest
import com.kdpark0723.event.scheduler.EventChannelScheduler
import com.kdpark0723.event.subscriber.EventWorker

class EventDispatcher(
    private val channelsScheduler: EventChannelScheduler,
    private val multiplexer: EventMultiplexer
) {
    init {
        addMultiplexerInChannels()
    }

    private fun addMultiplexerInChannels() {
        channelsScheduler.addSubscriberAll(multiplexer)
    }

    fun addWorker(worker: EventWorker): Boolean {
        return multiplexer.addWorker(worker)
    }

    fun removeWorker(worker: EventWorker): Boolean {
        return multiplexer.removeWorker(worker)
    }

    fun removeWorker(event: String): Boolean {
        return multiplexer.removeWorker(event)
    }

    fun push(request: EventRequest) {
        return channelsScheduler.push(request)
    }

    fun resolve() {
        channelsScheduler.resolveAll()
    }
}