package dev.vadzimv.testcoroutines.dagger

import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector

@Module(includes = [AndroidInjectionModule::class])
interface UiModule {
    @Binds
    fun bindisDispatchingAndroidInjector(impl: DispatchingAndroidInjector<Any>): AndroidInjector<Any>
}