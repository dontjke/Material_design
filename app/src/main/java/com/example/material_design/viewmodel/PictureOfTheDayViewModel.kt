package com.example.material_design.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.material_design.BuildConfig
import com.example.material_design.R
import com.example.material_design.model.PictureOfTheDayResponseData
import com.example.material_design.model.RepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class PictureOfTheDayViewModel(
    private val liveDataForViewToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: RepositoryImpl = RepositoryImpl()
) : ViewModel() {

    fun getLiveData(): MutableLiveData<AppState> {
        return liveDataForViewToObserve
    }


    @SuppressLint("SimpleDateFormat")
    fun sendRequestForPicture(date: Date) {
        liveDataForViewToObserve.value = AppState.Loading
        repositoryImpl.getPictureOfTheDayApi()
            .getPictureOfTheDayByData(BuildConfig.NASA_API_KEY, testFormatDate(date, "yyyy-MM-dd"))
            .enqueue(callback)
    }

    fun testFormatDate(date: Date, pattern: String): String {
        val format = SimpleDateFormat(pattern)
        return format.format(date)
    }


    private val callback = object : Callback<PictureOfTheDayResponseData> {
        override fun onResponse(
            call: Call<PictureOfTheDayResponseData>,
            response: Response<PictureOfTheDayResponseData>
        ) {
            if (response.isSuccessful && response.body() != null) {
                liveDataForViewToObserve.value = AppState.Success(response.body()!!)
            } else {
                val message = response.message()
                if (message.isNullOrEmpty()) {
                    liveDataForViewToObserve.value =
                        AppState.Error(Throwable(R.string.something_wrong.toString()))
                } else {
                    liveDataForViewToObserve.value =
                        AppState.Error(Throwable(message))
                }
            }
        }

        override fun onFailure(call: Call<PictureOfTheDayResponseData>, t: Throwable) {
            liveDataForViewToObserve.value = AppState.Error(t)
        }
    }
}