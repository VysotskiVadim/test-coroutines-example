package dev.vadzimv.testcoroutines.feature

import android.os.Bundle
import dev.vadzimv.testcoroutines.ExampleApplication
import dev.vadzimv.testcoroutines.R
import dev.vadzimv.testcoroutines.dagger.DaggerActivity
import dev.vadzimv.testcoroutines.feature.di.FeatureViewModel

class FeatureActivity : DaggerActivity(ExampleApplication::featureComponent) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature)
        val featureViewModel = getViewModel<FeatureViewModel>()
    }
}
