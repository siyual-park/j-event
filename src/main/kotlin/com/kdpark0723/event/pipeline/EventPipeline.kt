package com.kdpark0723.event.pipeline

import com.kdpark0723.event.event.Event

interface EventPipeline {
    fun process(event: Event): Event
}