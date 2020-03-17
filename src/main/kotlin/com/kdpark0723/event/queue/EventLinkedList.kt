package com.kdpark0723.event.queue

import com.kdpark0723.event.request.EventRequest
import java.util.*

class EventLinkedList : EventQueue, LinkedList<EventRequest>()