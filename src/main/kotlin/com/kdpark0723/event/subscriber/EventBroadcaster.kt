package com.kdpark0723.event.subscriber

interface EventBroadcaster : EventSubscriber {
    fun subscribe(subscriber: EventSubscriber): Boolean
    fun unsubscribe(subscriber: EventSubscriber): Boolean
}