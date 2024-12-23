package com.bagreeni.fetchrewardstakehome.backend

import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test

class FetchApiServiceTest {

    lateinit var service: FetchApiService
    private val mockWebServer = MockWebServer()

    @Test
    fun testHappyPathResponse() = runTest{
        val mockResponse =
            MockResponse().setResponseCode(200).setBody(FetchApiServiceTestData.happyPath200)
        mockWebServer.start()
        service = FetchApiService(FetchApiImpl(mockWebServer.url("/").toString()))
        mockWebServer.enqueue(mockResponse)

        val result = service.getInfo()
        val expectedResult = HiringResponse.Success(listOf(
            FetchItem(
                id = 755,
                listId = 2,
                name = ""
            ),
            FetchItem(
                id = 203,
                listId = 2,
                name = ""
            ),
            FetchItem(
                id = 284,
                listId = 1,
                name = "Item 684"
            ),
            FetchItem(
                id = 24,
                listId = 1,
                name = "Item 618"
            ),
            ))
        assertEquals(expectedResult, result)
    }

    @Test
    fun testNetworkError() = runTest{
        val mockResponse =
            MockResponse().setResponseCode(500)
        mockWebServer.start()
        service = FetchApiService(FetchApiImpl(mockWebServer.url("/").toString()))
        mockWebServer.enqueue(mockResponse)

        val result = service.getInfo()
        val expectedResult = HiringResponse.Failure
        assertEquals(expectedResult, result)
    }

    @Test
    fun testApiFailure() = runTest{
        val mockResponse =
            MockResponse().setResponseCode(404)
        mockWebServer.start()
        service = FetchApiService(FetchApiImpl(mockWebServer.url("/").toString()))
        mockWebServer.enqueue(mockResponse)

        val result = service.getInfo()
        val expectedResult = HiringResponse.ApiFailure(404)
        assertEquals(expectedResult, result)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

}