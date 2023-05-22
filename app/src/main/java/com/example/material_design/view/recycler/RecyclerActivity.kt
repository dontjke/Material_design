package com.example.material_design.view.recycler

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.material_design.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = arrayListOf(
            Data(Data.TYPE_HEADER, "Header"),
            Data(Data.TYPE_EARTH, "Earth"),
            Data(Data.TYPE_EARTH, "Earth"),
            Data(Data.TYPE_EARTH, "Earth"),
            Data(Data.TYPE_EARTH, "Earth"),
            Data(Data.TYPE_EARTH, "Earth"),
            Data(Data.TYPE_EARTH, "Earth"),
            Data(Data.TYPE_MARS, "Mars", ""),
            Data(Data.TYPE_EARTH, "Earth"),
            Data(Data.TYPE_EARTH, "Earth"),
            Data(Data.TYPE_EARTH, "Earth"),
            Data(Data.TYPE_MARS, "Mars", null)
        )

        binding.recyclerView.adapter = RecyclerActivityAdapter(
            object : OnListItemClickListener {
                override fun onItemClick(data: Data) {
                    Toast.makeText(this@RecyclerActivity, data.someText,
                        Toast.LENGTH_SHORT).show()
                }
            },
            data
        )

    }
}