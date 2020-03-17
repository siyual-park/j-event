package com.kdpark0723.event.queue

import com.kdpark0723.event.event.Event
import java.util.concurrent.ConcurrentLinkedQueue

class ConcurrentEventQueue : EventQueue, ConcurrentLinkedQueue<Event>()