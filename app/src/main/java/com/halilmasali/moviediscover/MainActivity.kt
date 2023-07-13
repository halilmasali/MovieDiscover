package com.halilmasali.moviediscover

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.halilmasali.moviediscover.dataRepository.DataRepository


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun test(view: View) {
        // TODO for data repo test
        val dataRepository = DataRepository(this,this)
        dataRepository.getSeriesAiringToday().observe(this) {airingToday ->
            if (airingToday != null) {
                Toast.makeText(this, "Airing Today ${airingToday[0].name}", Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(this, "Response null", Toast.LENGTH_LONG).show()
            }
        }
        dataRepository.getSeriesTopRated()
        dataRepository.getSeriesPopular()
        dataRepository.getSeriesOnTheAir()

        dataRepository.getMovieUpcoming()
        dataRepository.getMoviePopular()
        dataRepository.getMovieNowPlaying()
        dataRepository.getMovieTopRated()
    }
}