package com.example.material_design.model

import com.example.material_design.utils.KEY_FOR_EXPANDED_USAGE_API_KEY
import com.example.material_design.utils.KEY_FOR_EXPANDED_USAGE_DATE
import com.example.material_design.utils.RESPONSE_END_POINT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayAPI {
    @GET(RESPONSE_END_POINT)
    fun getPictureOfTheDay(@Query(KEY_FOR_EXPANDED_USAGE_API_KEY) apiKey: String): Call<PictureOfTheDayResponseData>
    @GET(RESPONSE_END_POINT)
    fun getPictureOfTheDayByData(@Query(KEY_FOR_EXPANDED_USAGE_API_KEY) apiKey: String, @Query(
        KEY_FOR_EXPANDED_USAGE_DATE) date: String): Call<PictureOfTheDayResponseData>
}