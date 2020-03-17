package com.kdpark0723.event.dispatcher

import com.kdpark0723.event.channel.EventChannel
import com.kdpark0723.event.multiplexer.EventMultiplexer
import com.kdpark0723.event.observer.EventWorker
import com.kdpark0723.event.request.EventRequest

class EventDispatcher(
    private val channel: EventChannel,
    private val multiplexer: EventMultiplexer
) {
    init {
        addMultiplexerInChannels()
    }

    private fun addMultiplexerInChannels() {
        channel.addReceiver(multiplexer)
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

    fun send(request: EventRequest) {
        return channel.send(request)
    }

    fun resolve() {
        channel.resolve()
    }
}