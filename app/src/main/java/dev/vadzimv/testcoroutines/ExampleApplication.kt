package dev.vadzimv.testcoroutines

import android.app.Application
import dev.vadzimv.testcoroutines.coroutines.DaggerCoroutinesDispatchersComponent
import dev.vadzimv.testcoroutines.feature.di.DaggerFeatureComponent
import dev.vadzimv.testcoroutines.feature.di.FeatureComponent

class ExampleApplication : Application() {

    val featureComponent: FeatureComponent = DaggerFeatureComponent.builder()
        .coroutinesDispatchersComponent(DaggerCoroutinesDispatchersComponent.create())
        .build()

    override fun onCreate() {
        super.onCreate()
    }
}