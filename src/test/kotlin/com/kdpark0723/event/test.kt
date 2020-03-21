package com.kdpark0723.event

import com.kdpark0723.event.broadcaster.ConcurrentEventBroadcaster
import com.kdpark0723.event.channel.TransferableEventQueueChannel
import com.kdpark0723.event.distributor.EventDistributor
import com.kdpark0723.event.event.Event
import com.kdpark0723.event.queue.ConcurrentEventQueue
import com.kdpark0723.event.scheduler.CompoundedEventScheduler
import com.kdpark0723.event.subscriber.EventSubscriber
import java.util.concurrent.Executors

fun main() {
    val broadcaster = ConcurrentEventBroadcaster()
    val executorService = Executors.newCachedThreadPool()
    val scheduler = CompoundedEventScheduler(broadcaster, executorService)
    val channel = TransferableEventQueueChannel(ConcurrentEventQueue())
    val eventDistributor = EventDistributor(channel, scheduler)

    val subscriber1 = object : EventSubscriber {
        override fun onEvent(event: Event) {
            if (event.source is Int) {
                val a = event.source as Int

                if (a > 10) {
                    println("Done.")
                    executorService.shutdown()
                    return
                }

                println("1: ${a}")
                eventDistributor.publishEvent(Event(a + 1))
            }
        }
    }
    val subscriber2 = object : EventSubscriber {
        override fun onEvent(event: Event) {
            if (event.source is Int) {
                val a = event.source as Int

                if (a > 10) {
                    println("Done.")
                    executorService.shutdown()
                    return
                }

                println("2: ${a}")
                eventDistributor.publishEvent(Event(a + 1))
            }
        }
    }

    broadcaster.subscribe(subscriber1)
    broadcaster.subscribe(subscriber2)

    eventDistributor.publishEvent(Event(0))

    while (!executorService.isTerminated) {
        eventDistributor.distribute()
    }
}