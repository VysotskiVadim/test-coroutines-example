package dev.vadzimv.testcoroutines.coroutines

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Module
class CoroutinesDispatchersModule {
    @ComputationDispatcher
    @Provides
    fun provideComputationDispatcher() = Dispatchers.Default

    @IODispatcher
    @Provides
    fun provideIODispatcher() = Dispatchers.IO
}