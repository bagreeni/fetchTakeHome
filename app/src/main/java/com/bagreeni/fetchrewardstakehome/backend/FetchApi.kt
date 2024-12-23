package com.bagreeni.fetchrewardstakehome.backend

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject

interface FetchApi{
    @GET("/hiring.json")
    suspend fun getHiringData() : Response<List<FetchItem>>
}

class FetchApiImpl @Inject constructor(baseUrl: String): FetchApi{
    val responseJson = Json{ ignoreUnknownKeys = true }
    private val retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(
        responseJson.asConverterFactory(
            MediaType.get("application/json; charset=UTF8")
        )
    )
    .build()
    .create(FetchApi::class.java)

    override suspend fun getHiringData(): Response<List<FetchItem>> {
        return retrofit.getHiringData()
    }
}