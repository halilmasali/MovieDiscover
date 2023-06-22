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
        apiConnection.getNowPlayingList()
        apiConnection.getPopularList()
        apiConnection.nowPlayingLiveData.observe(this) { nowPlayingList ->
            if (nowPlayingList != null) {
                Toast.makeText(this,"Total page ${nowPlayingList.totalPages}",Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this,"Response null",Toast.LENGTH_LONG).show()
            }
        }
        apiConnection.popularLiveData.observe(this) { popularList ->
            if (popularList != null) {
                Toast.makeText(this,"The most popular ${popularList.results[0].title}",Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this,"Response null",Toast.LENGTH_LONG).show()
            }
        }

    }
}