package com.halilmasali.moviediscover.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.halilmasali.moviediscover.R
import com.halilmasali.moviediscover.ui.fragments.CustomFragmentManager
import com.halilmasali.moviediscover.ui.fragments.MainFragment


class MainActivity : AppCompatActivity() {

    lateinit var customFragmentManager: CustomFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        customFragmentManager = CustomFragmentManager(supportFragmentManager, R.id.fragmentContainerView)
        customFragmentManager.replaceFragment(MainFragment())
    }
}