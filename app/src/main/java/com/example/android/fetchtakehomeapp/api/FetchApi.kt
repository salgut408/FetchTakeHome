package com.example.android.fetchtakehomeapp.api

import com.example.android.fetchtakehomeapp.models.JsonResponse
import retrofit2.Response
import retrofit2.http.GET

interface FetchApi {
    @GET("hiring.json")
    suspend fun getFetchInformation() : Response<List<JsonResponse>>
}