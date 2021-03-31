package com.ragini.navigationapplication

import android.os.Build
import com.ragini.navigationapplication.repository.PlacesRepository
import com.ragini.navigationapplication.viewmodel.PlacesViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(
    constants = BuildConfig::class,
    sdk = [Build.VERSION_CODES.LOLLIPOP],
    packageName = "com.ragini.navigationapplication"
)
class ViewModelTest {

    private lateinit var placeRepository: PlacesRepository

    private lateinit var viewModel: PlacesViewModel

    companion object {
        const val TOTAL_DATA_COUNT = 6
    }

    @Before
    fun setup() {
        placeRepository = PlacesRepository.getInstance(RuntimeEnvironment.application)
        viewModel = PlacesViewModel(placeRepository)
        viewModel.loadPlacesInDB()
    }

    @Test
    fun checkData() {
        placeRepository.getTotalPlaces().subscribe {
            Assert.assertNotEquals(it, 0)
        }
    }

    @Test
    fun checkCount() {
        placeRepository.getTotalPlaces().subscribe {
            Assert.assertEquals(it, TOTAL_DATA_COUNT)
        }
    }
}