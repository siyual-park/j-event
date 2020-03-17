package com.kdpark0723.event.dispatcher

import com.kdpark0723.event.channel.SyncEventChannel
import com.kdpark0723.event.multiplexer.SyncEventMultiplexer
import com.kdpark0723.event.queue.SyncEventQueue
import com.kdpark0723.event.scheduler.RoundRobinEventChannelScheduler

fun syncEventDispatcherFrom(channelCount: Int): EventDispatcher {
    val channels = MutableList(channelCount) {
        SyncEventChannel(
            SyncEventQueue()
        )
    }
    val scheduler = RoundRobinEventChannelScheduler(channels)
    val multiplexer = SyncEventMultiplexer()

    return EventDispatcher(scheduler, multiplexer)
}