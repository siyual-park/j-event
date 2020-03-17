package com.kdpark0723.event

import com.kdpark0723.event.channel.QueueTransferableEventChannel
import com.kdpark0723.event.distributor.EventDistributor
import com.kdpark0723.event.event.Event
import com.kdpark0723.event.queue.ConcurrentEventQueue
import com.kdpark0723.event.subscriber.EventSubscriber

fun main() {
    val eventDistributor = EventDistributor(
        QueueTransferableEventChannel(ConcurrentEventQueue())
    )

    eventDistributor.subscribe(object : EventSubscriber {
        override fun onEvent(event: Event) {
            val a = event.source as Int

            if (a > 100) {
                println("Done.")
                return
            }

            println(a)
            eventDistributor.emit(Event(a + 1))
        }
    })

    eventDistributor.emit(Event(0))
    eventDistributor.distribute()
}