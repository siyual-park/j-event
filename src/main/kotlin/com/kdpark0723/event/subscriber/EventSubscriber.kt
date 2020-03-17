package com.kdpark0723.event.subscriber

import com.kdpark0723.event.request.EventRequest

interface EventSubscriber {
    fun listener(request: EventRequest): Boolean
}