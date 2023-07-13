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

//        //region Movies observers
//        apiConnection.getMovieNowPlayingList().observe(this) { nowPlayingList ->
//            if (nowPlayingList != null) {
//                Toast.makeText(this, "Total page ${nowPlayingList.totalPages}", Toast.LENGTH_LONG)
//                    .show()
//            } else {
//                Toast.makeText(this, "Response null", Toast.LENGTH_LONG).show()
//            }
//        }
//
//        apiConnection.getMoviePopularList().observe(this) { popularList ->
//            if (popularList != null) {
//                Toast.makeText(
//                    this,
//                    "The most popular ${popularList.results[0].title}",
//                    Toast.LENGTH_LONG
//                ).show()
//            } else {
//                Toast.makeText(this, "Response null", Toast.LENGTH_LONG).show()
//            }
//        }
//
//        apiConnection.getMovieTopRatedList().observe(this) { topRatedList ->
//            if (topRatedList != null) {
//                Toast.makeText(
//                    this,
//                    "Top Rated ${topRatedList.results[0].title}",
//                    Toast.LENGTH_LONG
//                ).show()
//            } else {
//                Toast.makeText(this, "Response null", Toast.LENGTH_LONG).show()
//            }
//        }
//
//        apiConnection.getMovieUpcomingList().observe(this) { upcomingList ->
//            if (upcomingList != null)
//                Toast.makeText(this, "Upcoming ${upcomingList.results[0].title}", Toast.LENGTH_LONG)
//                    .show()
//            else
//                Toast.makeText(this, "Response null", Toast.LENGTH_LONG).show()
//        }
//
//        apiConnection.getMoviesSimilarList(25).observe(this) { similarMovies ->
//            if (similarMovies != null)
//                Toast.makeText(this, "Similar ${similarMovies.results[0].title}", Toast.LENGTH_LONG)
//                    .show()
//        }
//        //endregion
    }
}