package dev.vadzimv.testcoroutines.feature.di

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeatureViewModel @Inject constructor(
    private val useCase: FeatureUseCase
) : ViewModel() {
    private val _state = MutableLiveData<ScreenState>()
    val state by lazy<LiveData<ScreenState>> {
        _state.value = ScreenState.Loading
        viewModelScope.launch {
            _state.value = ScreenState.Loaded(useCase.getFeatureData())
        }
        _state
    }

    sealed class ScreenState {
        object Loading : ScreenState()
        data class Loaded(val data: FeatureData) : ScreenState()
    }
}