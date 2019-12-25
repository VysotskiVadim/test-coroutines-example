package dev.vadzimv.testcoroutines.infra

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import java.util.*
import kotlin.coroutines.CoroutineContext

class JobCheckingDispatcherWrapper(private val parent: CoroutineDispatcher) :
    CoroutineDispatcher() {
    private val jobs = Collections.newSetFromMap(WeakHashMap<Job, Boolean>())

    var completionEvent: (() -> Unit)? = null

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        context[Job]?.let { addNewJob(it) }
        parent.dispatch(context, block)
    }

    @InternalCoroutinesApi
    override fun dispatchYield(context: CoroutineContext, block: Runnable) {
        context[Job]?.let { addNewJob(it) }
        parent.dispatchYield(context, block)
    }

    private fun addNewJob(job: Job): Boolean {
        job.invokeOnCompletion {
            completionEvent?.invoke()
        }
        return jobs.add(job)
    }

    @ExperimentalCoroutinesApi
    override fun isDispatchNeeded(context: CoroutineContext): Boolean {
        context[Job]?.let { addNewJob(it) }
        return parent.isDispatchNeeded(context)
    }

    val isAnyJobRunning: Boolean
        get() {
            jobs.removeAll { !it.isActive }
            return jobs.isNotEmpty()
        }
}