package com.kdpark0723.event.multiplexer

import com.kdpark0723.event.subscriber.EventWorker

class SyncEventMultiplexer : EventMultiplexer() {
    private val workers: MutableMap<String, EventWorker> = mutableMapOf()

    override fun addWorker(worker: EventWorker): Boolean {
        if (workers[worker.event] !== null)
            return false

        workers[worker.event] = worker
        return true
    }

    override fun removeWorker(worker: EventWorker): Boolean {
        return workers.remove(worker.event) === worker
    }

    override fun removeWorker(event: String): Boolean {
        return workers.remove(event) !== null
    }

    override fun getWorker(event: String): EventWorker? {
        return workers[event]
    }
}