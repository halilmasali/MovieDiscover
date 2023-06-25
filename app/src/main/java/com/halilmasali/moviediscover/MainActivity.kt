package com.halilmasali.moviediscover

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.halilmasali.moviediscover.apiRepository.ApiConnection


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun test(view: View) {
        val apiConnection = ApiConnection()
        //Movies
        apiConnection.getNowPlayingList()
        apiConnection.getPopularList()
        apiConnection.getTopRatedList()
        apiConnection.getUpcomingList()
        apiConnection.getSimilarMovies(25)
        //Series
        apiConnection.getAiringTodayList()

        //Movies observers
        apiConnection.nowPlayingLiveData.observe(this) { nowPlayingList ->
            if (nowPlayingList != null) {
                Toast.makeText(this, "Total page ${nowPlayingList.totalPages}", Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(this, "Response null", Toast.LENGTH_LONG).show()
            }
        }
        apiConnection.popularLiveData.observe(this) { popularList ->
            if (popularList != null) {
                Toast.makeText(
                    this,
                    "The most popular ${popularList.results[0].title}",
                    Toast.LENGTH_LONG
                ).show()
                println(popularList.dates?.minimum)
            } else {
                Toast.makeText(this, "Response null", Toast.LENGTH_LONG).show()
            }
        }
        apiConnection.topRatedLiveData.observe(this) { topRatedList ->
            if (topRatedList != null)
                Toast.makeText(
                    this,
                    "Top rated ${topRatedList.results[0].title}",
                    Toast.LENGTH_LONG
                ).show()
            else
                Toast.makeText(this, "Response null", Toast.LENGTH_LONG).show()
        }
        apiConnection.upcomingLiveData.observe(this) { upcomingList ->
            if (upcomingList != null)
                Toast.makeText(this, "Upcoming ${upcomingList.results[0].title}", Toast.LENGTH_LONG)
                    .show()
            else
                Toast.makeText(this, "Response null", Toast.LENGTH_LONG).show()
        }
        apiConnection.similarMoviesLiveData.observe(this) {similarMovies ->
            if (similarMovies != null)
                Toast.makeText(this, "Similar ${similarMovies.results[0].title}", Toast.LENGTH_LONG)
                    .show()
        }
        //Series observers
        apiConnection.airingTodayLiveData.observe(this) {airingTodayList->
            if (airingTodayList != null)
                Toast.makeText(this, "Airing Today: ${airingTodayList.results[0].name}", Toast.LENGTH_LONG)
                    .show()
            else
                Toast.makeText(this, "Response null", Toast.LENGTH_LONG).show()
        }
    }
}