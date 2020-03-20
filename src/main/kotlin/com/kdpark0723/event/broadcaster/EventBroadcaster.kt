package com.kdpark0723.event.broadcaster

import com.kdpark0723.event.subscriber.EventSubscriber

interface EventBroadcaster : EventSubscriber {
    fun subscribe(subscriber: EventSubscriber): Boolean
    fun unsubscribe(subscriber: EventSubscriber): Boolean
}