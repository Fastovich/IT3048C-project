package com.example.placefinder

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.placefinder.dto.Address
import com.example.placefinder.service.ParkService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.DelicateCoroutinesApi
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
    var allParks : List<Address>? = ArrayList<Address>()

    @OptIn(DelicateCoroutinesApi::class)
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
    fun `Given a park dto when City = Wilberforce then park = stateCode OH and City Wilberforce`(){
        var park = Address(stateCode = "OH",city = "Wilberforce")
        assertTrue(park.stateCode.equals("OH") )
        assertTrue(park.city.equals("Wilberforce"))

    }

    @Test
    fun `Given a park dto when stateCode = OH and City = Wilberforce then output OH Wilberforce`(){
        var park = Address(stateCode = "OH", city = "Wilberforce")
        assertTrue(park.toString().equals( "OH Wilberforce" ))
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
    fun `Given a view model with live data when populated with Parks then results should contain Wilberforce`() {
        givenViewModelIsInitializedWithMockData()
        whenJSONDataIsReadAndParsed()
        thenResultsShouldContainCharlesYoungBuffaloSoldiersNationalMonument()
    }

    private fun givenViewModelIsInitializedWithMockData() {
        val parks = ArrayList<Address>()
        parks.add(Address(stateCode = "OH", city = "Wilberforce"))

        coEvery { mockParkService.fetchParks() } returns parks

        mvm.parkService = mockParkService
    }

    private fun whenJSONDataIsReadAndParsed(){
        mvm.fetchParks()
    }

    private fun thenResultsShouldContainCharlesYoungBuffaloSoldiersNationalMonument(){
        var allParks : List<Address>? = ArrayList<Address>()
        val latch = CountDownLatch(1);
        val observer = object : Observer<List<Address>> {
            override fun onChanged(t: List<Address>) {
                allParks = t
                latch.countDown()
                mvm.parks.removeObserver(this)
            }
        }
        mvm.parks.observeForever(observer)

        latch.await(1, TimeUnit.SECONDS)
        assertNotNull(allParks)
        assertTrue(allParks!!.contains(Address(stateCode = "OH", city = "Wilberforce")))
    }
}