package com.kdpark0723.event.channel

import com.kdpark0723.event.event.Event

interface CanSendEventChannel : EventChannel {
    fun send(event: Event)
}