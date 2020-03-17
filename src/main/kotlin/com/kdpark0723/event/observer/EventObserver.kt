package com.kdpark0723.event.observer

import com.kdpark0723.event.request.EventRequest

interface EventObserver {
    fun listener(request: EventRequest)
}