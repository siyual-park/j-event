package com.kdpark0723.event

import com.kdpark0723.event.dispatcher.syncEventDispatcherFrom
import com.kdpark0723.event.request.EventRequest
import com.kdpark0723.event.subscriber.eventWorkerFrom

fun main() {
    val dispatcher = syncEventDispatcherFrom(10)

    dispatcher.addWorker(eventWorkerFrom("add") { a: Int, b: Int ->
        println(a + b)
    })

    dispatcher.addWorker(eventWorkerFrom("minus") { a: Int, b: Int ->
        println(a - b)
    })

    repeat(10) {
        dispatcher.push(EventRequest("add", arrayOf(it, it * 2)))
        dispatcher.push(EventRequest("minus", arrayOf(it, it * 2)))
    }

    dispatcher.resolve()
}