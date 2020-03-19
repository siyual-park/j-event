package com.kdpark0723.event

import com.kdpark0723.event.channel.QueueTransferableEventChannel
import com.kdpark0723.event.distributor.EventDistributor
import com.kdpark0723.event.event.Event
import com.kdpark0723.event.queue.ConcurrentEventQueue
import com.kdpark0723.event.subscriber.EventSubscriber
import com.kdpark0723.event.subscriber.SingleEventBroadcaster

fun main() {
    val eventDistributor = EventDistributor(
        QueueTransferableEventChannel(ConcurrentEventQueue()),
        SingleEventBroadcaster()
    )
    val subscriber1 = object : EventSubscriber {
        override fun onEvent(event: Event) {
            val a = event.source as Int

            if (a > 10) {
                println("Done.")
                return
            }

            println("1: ${a}")
            eventDistributor.publishEvent(Event(a + 1))
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
            eventDistributor.publishEvent(Event(a + 1))
        }
    }

    eventDistributor.subscribe(subscriber1)
    eventDistributor.subscribe(subscriber2)

    eventDistributor.publishEvent(Event(0))

    eventDistributor.distribute()
}