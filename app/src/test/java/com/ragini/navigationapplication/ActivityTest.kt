package com.ragini.navigationapplication

import android.content.res.Configuration
import android.os.Build
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.util.ActivityController

@RunWith(RobolectricTestRunner::class)
@Config(
    constants = BuildConfig::class,
    sdk = [Build.VERSION_CODES.LOLLIPOP],
    packageName = "com.ragini.navigationapplication"
)
class ActivityTest {
    private lateinit var controller: ActivityController<MainActivity>

    @Test
    @Config(qualifiers = "+port")
    fun testOrientationChange() {
        controller = Robolectric.buildActivity(MainActivity::class.java)
        controller.setup()

        RuntimeEnvironment.setQualifiers("+land")


        controller.configurationChange(Configuration())

    }

}