package dev.vadzimv.testcoroutines.feature.di

import dagger.Component
import dagger.android.HasAndroidInjector
import dev.vadzimv.testcoroutines.coroutines.CoroutinesDispatchersComponent
import dev.vadzimv.testcoroutines.dagger.UiModule

@Component(
    dependencies = [CoroutinesDispatchersComponent::class],
    modules = [FeatureModule::class, UiModule::class]
)
interface FeatureComponent : HasAndroidInjector