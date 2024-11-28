package com.example.appnoticias.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.appnoticias.data.dto.NYTResponse

interface NYTApi {
    @GET("{category}.json")
    suspend fun getTopStories(
        @Path("category") category: String,
        @Query("api-key") apiKey: String
    ): Response<NYTResponse>
}
