package com.example.placefinder

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.placefinder.dto.Park
import com.example.placefinder.service.ParkService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ParkUnitTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var mvm: MainViewModel
    private lateinit var parkService: ParkService
    var allParks : List<Park>? = ArrayList<Park>()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @MockK
    lateinit var mockParkService: ParkService

    @Before
    fun populateParks(){
        Dispatchers.setMain(mainThreadSurrogate)
        MockKAnnotations.init(this)
        mvm = MainViewModel()

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `Given a park dto when stateCode is WY and cityName is Yellowstone National Park then StateCode is WY and city name is Yellowstone National Park`(){
        var park = Park("WY", "Yellowstone National Park")
        assertTrue(park.stateCode.equals("WY"))
        assertTrue(park.cityName.equals("Yellowstone National Park"))
    }

    @Test
    fun `Given a park dto when stateCode is WY and cityName is Yellowstone National Park then output is city state`(){
        var park = Park("WY", "Yellowstone National Park")
        assertTrue(park.toString().equals("Yellowstone National Park WY"))
    }

    @Test
    fun `Given service connects to Parks JSON stream when data is read and parsed then park collection should be greater then zero`() = runTest {
        launch(Dispatchers.Main) {
            givenParkServiceIsInitialized()
            whenServiceDataIsReadAndParsed()
            thenTheParkCollectionSizeShouldBeGreaterThenZero()
        }
    }

    private fun givenParkServiceIsInitialized() {
        parkService = ParkService()
    }

    private suspend fun whenServiceDataIsReadAndParsed(){
        allParks = parkService.fetchParks()
    }

    private fun thenTheParkCollectionSizeShouldBeGreaterThenZero(){
        assertNotNull(allParks)
        assertTrue(allParks!!.isNotEmpty())
    }

    @Test
    fun `Given a view model with live data when populated with Parks then results should contain Charles Young Buffalo Soldiers National Monument`() {
        givenViewModelIsInitializedWithMockData()
        whenJSONDataIsReadAndParsed()
        thenResultsShouldContainCharlesYoungBuffaloSoldiersNationalMonument()
    }

    private fun givenViewModelIsInitializedWithMockData() {
        val parks = ArrayList<Park>()
        parks.add(Park("OH","Charles Young Buffalo Soldiers National Monument"))
        parks.add(Park("WY","Yellowstone National Park Headquarters"))
        parks.add(Park("CA", "San Francisco"))

        coEvery { mockParkService.fetchParks() } returns parks

        mvm.parkService = mockParkService
    }

    private fun whenJSONDataIsReadAndParsed(){
        mvm.fetchParks()
    }

    private fun thenResultsShouldContainCharlesYoungBuffaloSoldiersNationalMonument(){
        var allParks : List<Park>? = ArrayList<Park>()
        val latch = CountDownLatch(1);
        val observer = object : Observer<List<Park>> {
            override fun onChanged(t: List<Park>) {
                allParks = t
                latch.countDown()
                mvm.parks.removeObserver(this)
            }
        }
        mvm.parks.observeForever(observer)

        latch.await(1, TimeUnit.SECONDS)
        assertNotNull(allParks)
        assertTrue(allParks!!.contains(Park("OH", "Charles Young Buffalo Soldiers National Monument")))
    }
}
