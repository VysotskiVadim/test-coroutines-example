package dev.vadzimv.testcoroutines.feature

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import dev.vadzimv.testcoroutines.ExampleApplication
import dev.vadzimv.testcoroutines.R
import dev.vadzimv.testcoroutines.dagger.DaggerActivity
import dev.vadzimv.testcoroutines.feature.di.FeatureData
import dev.vadzimv.testcoroutines.feature.di.FeatureViewModel
import kotlinx.android.synthetic.main.activity_feature.*

class FeatureActivity : DaggerActivity(ExampleApplication::featureComponent) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature)
        getViewModel<FeatureViewModel>().state.observe(this) {
            when (it) {
                FeatureViewModel.ScreenState.Loading -> showLoading()
                is FeatureViewModel.ScreenState.Loaded -> showData(it.data)
            }
        }
    }

    private fun showLoading() {
        textView.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun showData(featureData: FeatureData) {
        textView.text = featureData.data
        textView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }
}
