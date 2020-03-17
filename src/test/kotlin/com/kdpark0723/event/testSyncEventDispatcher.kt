package com.kdpark0723.event

import com.kdpark0723.event.dispatcher.syncEventDispatcherFrom
import com.kdpark0723.event.observer.eventWorkerFrom
import com.kdpark0723.event.request.EventRequest

fun main() {
    val dispatcher = syncEventDispatcherFrom(10)

    dispatcher.addWorker(eventWorkerFrom("pass") { a: Int ->
        if (a > 100) {
            println("Done!")
            return@eventWorkerFrom
        }
        println(a)

        dispatcher.send(EventRequest("pass", arrayOf(a + 1)))
        dispatcher.resolve()
    })

    dispatcher.send(EventRequest("pass", arrayOf(1)))
    dispatcher.resolve()
}