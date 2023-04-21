package com.example.material_design.model

import com.example.material_design.utils.KEY_FOR_EXPANDED_USAGE
import com.example.material_design.utils.RESPONSE_END_POINT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayAPI {
    @GET(RESPONSE_END_POINT)
    fun getPictureOfTheDay(@Query(KEY_FOR_EXPANDED_USAGE) apiKey: String): Call<PictureOfTheDayResponseData>
}