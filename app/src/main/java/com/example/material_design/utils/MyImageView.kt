package com.example.material_design.utils

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class MyImageView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) :
    AppCompatImageView(context, attributeSet) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}