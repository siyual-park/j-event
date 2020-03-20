package com.kdpark0723.event

import com.kdpark0723.event.broadcaster.ConcurrentEventBroadcaster
import com.kdpark0723.event.channel.TransferableEventQueueChannel
import com.kdpark0723.event.distributor.EventDistributor
import com.kdpark0723.event.event.Event
import com.kdpark0723.event.event.NamedSource
import com.kdpark0723.event.queue.ConcurrentEventQueue
import com.kdpark0723.event.scheduler.CompoundedEventScheduler
import com.kdpark0723.event.subscriber.EventSubscriber
import com.kdpark0723.event.subscriber.ExecutorServiceShutdownEventSubscriber
import com.kdpark0723.event.subscriber.executorServiceShutdownEvent
import java.util.concurrent.Executors

fun main() {
    val executorService = Executors.newCachedThreadPool()
    val broadcaster = ConcurrentEventBroadcaster()
    val scheduler = CompoundedEventScheduler(broadcaster, executorService)
    val channel = TransferableEventQueueChannel(ConcurrentEventQueue())
    val eventDistributor = EventDistributor(channel, scheduler)

    val subscriber1 = object : EventSubscriber {
        override fun onEvent(event: Event) {
            if (event.source is Int) {
                val a = event.source as Int

                if (a > 10) {
                    println("Done.")
                    eventDistributor.publishEvent(Event(NamedSource(executorServiceShutdownEvent, executorService)))
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
                    eventDistributor.publishEvent(Event(NamedSource(executorServiceShutdownEvent, executorService)))
                    return
                }

                println("2: ${a}")
                eventDistributor.publishEvent(Event(a + 1))
            }
        }
    }
    val executorServiceShutdownEventSubscriber = ExecutorServiceShutdownEventSubscriber()

    broadcaster.subscribe(subscriber1)
    broadcaster.subscribe(subscriber2)
    broadcaster.subscribe(executorServiceShutdownEventSubscriber)

    eventDistributor.publishEvent(Event(0))

    while (!executorService.isShutdown) {
        eventDistributor.distribute()
    }
}