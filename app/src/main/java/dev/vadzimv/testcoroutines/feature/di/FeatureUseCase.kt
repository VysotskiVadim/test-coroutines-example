package dev.vadzimv.testcoroutines.feature.di

import dev.vadzimv.testcoroutines.coroutines.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class FeatureData(val data: String)

interface FeatureUseCase {
    suspend fun getFeatureData(): FeatureData
}

class FeatureUseCaseImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
): FeatureUseCase {
    override suspend fun getFeatureData(): FeatureData = withContext(ioDispatcher) {
        delay(1500)
        FeatureData("boo")
    }
}