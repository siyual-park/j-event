package com.kdpark0723.event.subscriber

import com.kdpark0723.event.event.Event

interface EventSubscriber {
    fun onEvent(event: Event)
}