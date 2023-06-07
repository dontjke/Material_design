package com.example.material_design.view

import android.os.Bundle
import android.view.View
import com.example.material_design.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class YouTube : YouTubeBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_you_tube)


        val youTubePlayerView: YouTubePlayerView = findViewById(R.id.youtubePlayer)
        val listener = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1?.loadVideo("qAHMCZBwYo4")
                p1?.play()
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Snackbar.make(View(applicationContext), "Initialization Failed", Snackbar.LENGTH_LONG).show()
            }
        }

        youTubePlayerView.initialize("AIzaSyAHChEnZq6kCSXt7sZtfJXpgzSeTUT5Plw",listener)
    }


}