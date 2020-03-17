package com.kdpark0723.event.publisher

import com.kdpark0723.event.event.Event

interface EventPublisher {
    fun publishEvent(event: Event)
}