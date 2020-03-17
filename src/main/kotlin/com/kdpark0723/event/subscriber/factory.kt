package com.kdpark0723.event.subscriber

inline fun <reified P1 : Any> eventWorkerFrom(event: String, crossinline worker: (p1: P1) -> Unit): EventWorker {
    return EventWorkerImpl(event) {
        if (it.parameters[0] !is P1)
            return@EventWorkerImpl false

        worker(it.parameters[0] as P1)
        return@EventWorkerImpl true
    }
}

inline fun <reified P1 : Any, reified P2 : Any> eventWorkerFrom(
    event: String,
    crossinline worker: (p1: P1, p2: P2) -> Unit
): EventWorker {
    return EventWorkerImpl(event) {
        if (it.parameters[0] !is P1 || it.parameters[1] !is P2)
            return@EventWorkerImpl false

        worker(it.parameters[0] as P1, it.parameters[1] as P2)
        return@EventWorkerImpl true
    }
}

inline fun <reified P1 : Any, reified P2 : Any, reified P3 : Any> eventWorkerFrom(
    event: String,
    crossinline worker: (p1: P1, p2: P2, p3: P3) -> Unit
): EventWorker {
    return EventWorkerImpl(event) {
        if (it.parameters[0] !is P1 || it.parameters[1] !is P2 || it.parameters[1] !is P3)
            return@EventWorkerImpl false

        worker(it.parameters[0] as P1, it.parameters[1] as P2, it.parameters[1] as P3)
        return@EventWorkerImpl true
    }
}