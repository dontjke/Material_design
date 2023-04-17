package com.example.material_design.viewmodel

import androidx.lifecycle.ViewModel
import com.example.material_design.BuildConfig
import com.example.material_design.model.PictureOfTheDayResponseData
import com.example.material_design.model.RepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PictureOfTheDayViewModel(private val repositoryImpl: RepositoryImpl = RepositoryImpl()): ViewModel() {

    fun sendRequest(){
        repositoryImpl.getPictureOfTheDayApi().getPictureOfTheDay(BuildConfig.NASA_API_KEY).enqueue(callback)
    }

    private val callback =  object : Callback<PictureOfTheDayResponseData>{
        override fun onResponse(
            call: Call<PictureOfTheDayResponseData>,
            response: Response<PictureOfTheDayResponseData>
        ) {
            response.body()!!
        }

        override fun onFailure(call: Call<PictureOfTheDayResponseData>, t: Throwable) {
            TODO("Not yet implemented")
        }
    }
}