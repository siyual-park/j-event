package com.kdpark0723.event.channel

interface EventChannel {
    fun isClose(): Boolean
    fun close()

    fun isEmpty(): Boolean
    fun isNotEmpty(): Boolean
}