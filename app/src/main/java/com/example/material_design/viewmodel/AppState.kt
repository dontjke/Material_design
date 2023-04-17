package com.example.material_design.viewmodel

import com.example.material_design.model.PictureOfTheDayResponseData

sealed class AppState {
    data class Success(val PictureOfTheDayResponseData: PictureOfTheDayResponseData) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}