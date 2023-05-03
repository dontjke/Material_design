package com.example.material_design.view.viewpager

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.material_design.R


class EarthFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_earth, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val desaturateEffect = RenderEffect.createColorFilterEffect(
                ColorMatrixColorFilter(
                    ColorMatrix().apply {
                        setSaturation(0f)
                    }
                )
            )
            view.findViewById<ImageView>(R.id.imageView).setRenderEffect(desaturateEffect)
        }


    }

}