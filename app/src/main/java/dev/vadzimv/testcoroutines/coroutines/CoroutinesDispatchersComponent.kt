package dev.vadzimv.testcoroutines.coroutines

import dagger.Component
import kotlinx.coroutines.CoroutineDispatcher

@Component(modules = [CoroutinesDispatchersModule::class])
interface CoroutinesDispatchersComponent {
    @ComputationDispatcher
    fun provideComputationDispatcher(): CoroutineDispatcher

    @IODispatcher
    fun provideIODispatcher(): CoroutineDispatcher
}

