package com.kdpark0723.event

import com.kdpark0723.event.dispatcher.syncEventDispatcherFrom
import com.kdpark0723.event.observer.eventWorkerFrom
import com.kdpark0723.event.request.EventRequest

fun main() {
    val dispatcher = syncEventDispatcherFrom(10)

    dispatcher.addWorker(eventWorkerFrom("pass") { a: Int ->
        println(a)
    })

    repeat(10) {
        dispatcher.send(EventRequest("pass", arrayOf(it)))
        dispatcher.send(EventRequest("pass", arrayOf(it)))
    }

    dispatcher.resolve()
}