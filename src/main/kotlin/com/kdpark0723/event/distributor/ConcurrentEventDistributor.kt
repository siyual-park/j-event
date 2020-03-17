package com.kdpark0723.event.distributor

import com.kdpark0723.event.channel.TransferableEventChannel
import com.kdpark0723.event.event.Event
import java.util.Collections.synchronizedList
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future

class ConcurrentEventDistributor(
    channel: TransferableEventChannel,
    private val executorService: ExecutorService
) : EventDistributor(channel) {
    private val runningJobs: MutableList<Future<*>> = synchronizedList(mutableListOf())
    private val endJobs: MutableList<Future<*>> = synchronizedList(mutableListOf())

    override fun distribute() {
        executorService.submit {
            while (channel.isNotEmpty()) {
                onEvent(channel.receive())

                var isDone = false
                while (!isDone) {
                    runningJobs.forEach { if (it.isDone) endJobs.add(it) }
                    runningJobs.removeAll(endJobs)
                    isDone = endJobs.isNotEmpty()
                    endJobs.clear()
                }
            }

            while (runningJobs.isNotEmpty()) {
                runningJobs.forEach { if (it.isDone) endJobs.add(it) }
                runningJobs.removeAll(endJobs)

                endJobs.clear()
            }

            executorService.shutdown()
        }
    }

    override fun onEvent(event: Event) {
        subscribers.forEach { subscriber ->
            runningJobs.add(executorService.submit { subscriber.onEvent(event) })
        }
    }
}