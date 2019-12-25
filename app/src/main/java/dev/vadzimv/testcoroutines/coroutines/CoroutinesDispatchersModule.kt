package dev.vadzimv.testcoroutines.coroutines

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

object CustomCoroutinesDispatchers {
    fun reset() {
        Default = Dispatchers.Default
        IO = Dispatchers.IO
    }

    var Default = Dispatchers.Default
    var IO = Dispatchers.IO
}

@Module
class CoroutinesDispatchersModule {

    @ComputationDispatcher
    @Provides
    fun provideComputationDispatcher() = CustomCoroutinesDispatchers.Default

    @IODispatcher
    @Provides
    fun provideIODispatcher() = CustomCoroutinesDispatchers.IO
}