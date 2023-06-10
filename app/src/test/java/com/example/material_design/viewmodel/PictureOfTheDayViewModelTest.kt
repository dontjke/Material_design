package com.example.material_design.viewmodel

import org.junit.Test
import java.util.*

class PictureOfTheDayViewModelTest {

    @Test
    fun testFormatDate() {
       val viewModel = PictureOfTheDayViewModel()
        val date2 = Calendar.getInstance()
        date2.set(2022,6-1,1,11,11)
        val date3 = Calendar.getInstance()
        date3.set(2033,11-1,24)
        val pattern1 = "yyyy-MM-dd"
        val pattern2 = "yyyy-MM-dd HH:mm"
        val pattern3 = "dd/MM/yyyy"
       val test =  viewModel.testFormatDate(date3.time,pattern3)
        assert(viewModel.testFormatDate(date3.time,pattern3) == "24/11/2033")
        assert(viewModel.testFormatDate(date3.time,pattern1) == "2033-11-24")
        assert(viewModel.testFormatDate(date2.time,pattern2) == "2022-06-01 11:11")
    }

}