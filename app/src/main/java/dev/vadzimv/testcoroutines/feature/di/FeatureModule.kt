package dev.vadzimv.testcoroutines.feature.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import dev.vadzimv.testcoroutines.dagger.ViewModelKey
import dev.vadzimv.testcoroutines.feature.FeatureActivity

@Module
interface FeatureModule {
    @ContributesAndroidInjector
    fun contributeFeatureActivity(): FeatureActivity

    @Binds
    @IntoMap
    @ViewModelKey(FeatureViewModel::class)
    fun bindFeatureViewModel(vm: FeatureViewModel): ViewModel

    @Binds
    fun bindUseCase(impl: FeatureUseCaseImpl): FeatureUseCase
}