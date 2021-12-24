package com.example.android.navigation.mainTasksPage

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import com.example.android.navigation.R
import com.example.android.navigation.mainTasksPage.MainTasksPageFragment
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyTestSuite {
    @Test fun testIndividualTestFragment() {
        // The "fragmentArgs" arguments are optional.
        val fragmentArgs = bundleOf("numElements" to 0)
        val scenario = launchFragment<MainTasksPageFragment>(fragmentArgs, R.style.AppTheme)
        scenario.moveToState(Lifecycle.State.STARTED)
        Assert.assertNotNull(scenario);

    }

    @Test fun testMainTasksPageFragment() {
        // The "fragmentArgs" arguments are optional.
        val fragmentArgs = bundleOf("numElements" to 0)
        val scenario = launchFragment<MainTasksPageFragment>(fragmentArgs, R.style.AppTheme)
        scenario.moveToState(Lifecycle.State.DESTROYED)
        Assert.assertNotNull(scenario);

    }


}