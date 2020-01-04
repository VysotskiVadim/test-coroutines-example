package dev.vadzimv.testcoroutines.infra

import androidx.test.espresso.IdlingResource

class CoroutinesIdlingResoruce(
    private val dispatcher: JobCheckingDispatcherWrapper
) : IdlingResource {
    override fun getName(): String = "Coroutines Idling Resource"

    override fun isIdleNow(): Boolean = dispatcher.isAnyJobRunning.not()

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        dispatcher.completionEvent = {
            callback.onTransitionToIdle()
        }
    }
}