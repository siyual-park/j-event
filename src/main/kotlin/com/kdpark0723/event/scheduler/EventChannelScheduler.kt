package com.kdpark0723.event.scheduler

import com.kdpark0723.event.channel.EventChannel

abstract class EventChannelScheduler(
    protected open val channels: Collection<EventChannel>
) : EventChannel