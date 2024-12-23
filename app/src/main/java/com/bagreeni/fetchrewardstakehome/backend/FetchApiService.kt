package com.bagreeni.fetchrewardstakehome.backend

import javax.inject.Inject

class FetchApiService @Inject constructor(private val api: FetchApi) {
    suspend fun getInfo() : HiringResponse {
        return try {
            val response = api.getHiringData()
            if(response.isSuccessful){
                return HiringResponse.Success(response.body() ?: emptyList())
            } else if (response.code() == 500){
                return HiringResponse.Failure
            }
            // was a failure, return code.
            return HiringResponse.ApiFailure(response.code())
        } catch (e : Exception){
            return HiringResponse.Failure
        }
    }
}

