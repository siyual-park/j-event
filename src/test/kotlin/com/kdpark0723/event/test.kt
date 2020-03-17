package com.kdpark0723.event

import com.kdpark0723.event.channel.QueueTransferableEventChannel
import com.kdpark0723.event.distributor.ConcurrentEventDistributor
import com.kdpark0723.event.event.Event
import com.kdpark0723.event.queue.ConcurrentEventQueue
import com.kdpark0723.event.subscriber.EventSubscriber
import java.util.concurrent.Executors

fun main() {
    val eventDistributor = ConcurrentEventDistributor(
        QueueTransferableEventChannel(ConcurrentEventQueue()),
        Executors.newCachedThreadPool()
    )
    val subscriber1 = object : EventSubscriber {
        override fun onEvent(event: Event) {
            val a = event.source as Int

            if (a > 10) {
                println("Done.")
                return
            }

            println("1: ${a}")
            eventDistributor.emit(Event(a + 1))
        }
    }
    val subscriber2 = object : EventSubscriber {
        override fun onEvent(event: Event) {
            val a = event.source as Int

            if (a > 10) {
                println("Done.")
                return
            }

            println("2: ${a}")
            eventDistributor.emit(Event(a + 1))
        }
    }

    eventDistributor.subscribe(subscriber1)
    eventDistributor.subscribe(subscriber2)

    eventDistributor.emit(Event(0))
    eventDistributor.distribute()
}