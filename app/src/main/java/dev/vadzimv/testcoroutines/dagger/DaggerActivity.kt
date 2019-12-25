package dev.vadzimv.testcoroutines.dagger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import dagger.android.HasAndroidInjector
import dev.vadzimv.testcoroutines.ExampleApplication
import javax.inject.Inject

abstract class DaggerActivity(
    private val injector: (app: ExampleApplication) -> HasAndroidInjector
) : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.invoke(application as ExampleApplication).androidInjector().inject(this)
        super.onCreate(savedInstanceState)
    }

    inline fun <reified TViewModel : ViewModel> getViewModel(): TViewModel {
        val viewModelProvider = ViewModelProviders.of(this, viewModelFactory)
        return viewModelProvider[TViewModel::class.java]
    }
}