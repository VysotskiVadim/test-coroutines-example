package dev.vadzimv.testcoroutines

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import dev.vadzimv.testcoroutines.feature.FeatureActivity
import dev.vadzimv.testcoroutines.infra.DispatcherIdlerRule
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FeatureTest {

    @get:Rule
    val rules: TestRule = RuleChain
        .outerRule(DispatcherIdlerRule())
        .around(ActivityTestRule(FeatureActivity::class.java))

    @Test
    fun testDataUpdated() {
        onView(withId(R.id.textView)).check(matches(withText("boo")))
    }
}
