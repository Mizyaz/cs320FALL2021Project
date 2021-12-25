package com.example.android.navigation.individualTaskPage

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.Lifecycle
import androidx.test.runner.AndroidJUnit4
import com.example.android.navigation.R
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IndividualTaskFragmentTest {
    @Test
    fun testIndividualTaskFragment() {
        // The "fragmentArgs" arguments are optional.
        val fragmentArgs = bundleOf("taskId" to "0")
        val scenario = launchFragment<IndividualTaskPage>(fragmentArgs, R.style.AppTheme)
        fragmentArgs.equals("0")
        fragmentArgs.hashCode()
        scenario.moveToState(Lifecycle.State.STARTED)
        Assert.assertNotNull(scenario);

    }

}