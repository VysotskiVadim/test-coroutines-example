package dev.vadzimv.testcoroutines.infra

import androidx.test.espresso.IdlingRegistry
import dev.vadzimv.testcoroutines.coroutines.CustomCoroutinesDispatchers
import kotlinx.coroutines.Dispatchers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class DispatcherIdlerRule: TestRule {
    override fun apply(base: Statement?, description: Description?): Statement =
        object : Statement() {
            override fun evaluate() {
                val espressoTrackedDispatcherIO = JobCheckingDispatcherWrapper(Dispatchers.IO)
                val ioIdler = CoroutinesIdlingResoruce(espressoTrackedDispatcherIO)
                IdlingRegistry.getInstance().register(ioIdler)
                CustomCoroutinesDispatchers.IO = espressoTrackedDispatcherIO

                val espressoTrackedDispatcherDefault = JobCheckingDispatcherWrapper(Dispatchers.Default)
                val defaultIdler = CoroutinesIdlingResoruce(espressoTrackedDispatcherDefault)
                IdlingRegistry.getInstance().register(defaultIdler)
                CustomCoroutinesDispatchers.Default = espressoTrackedDispatcherDefault

                try {
                    base?.evaluate()
                } finally {
                    IdlingRegistry.getInstance().unregister(ioIdler)
                    IdlingRegistry.getInstance().unregister(defaultIdler)
                    CustomCoroutinesDispatchers.reset()
                }
            }
        }
}