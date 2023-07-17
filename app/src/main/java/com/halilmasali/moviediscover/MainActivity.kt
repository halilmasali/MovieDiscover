package com.halilmasali.moviediscover

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.halilmasali.moviediscover.dataRepository.DataRepository
import com.halilmasali.moviediscover.dataRepository.apiRepository.ExceptionHandler


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
            if (airingToday.data != null) {
                Toast.makeText(this, "Airing Today ${airingToday.data!![0].name}", Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(this, "Response null: ${ExceptionHandler.handleError(airingToday.error)}", Toast.LENGTH_LONG).show()
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