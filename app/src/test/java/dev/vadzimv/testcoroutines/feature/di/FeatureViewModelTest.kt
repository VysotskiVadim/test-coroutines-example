package dev.vadzimv.testcoroutines.feature.di

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import dev.vadzimv.testcoroutines.infra.BaseViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class FeatureViewModelTest : BaseViewModelTest() {
    private val useCaseMock = mock<FeatureUseCase>()
    private val featureViewModel = FeatureViewModel(useCaseMock)

    @Test
    fun `should load feature data`() = runBlockingTest {
        whenever(useCaseMock.getFeatureData()).thenReturn(FeatureData("test"))
        assertEquals(
            FeatureViewModel.ScreenState.Loaded(FeatureData("test")),
            featureViewModel.state.value
        )
    }
}