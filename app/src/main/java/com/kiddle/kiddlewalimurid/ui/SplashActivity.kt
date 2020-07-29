package com.kiddle.kiddlewalimurid.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.onboarding.OnboardingPresensi
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val animation = AnimationUtils.loadAnimation(this, R.anim.splash)
        logo_splash.startAnimation(animation)

        Handler().postDelayed({
            startActivity(Intent(this, OnboardingPresensi::class.java))
        }, 1000)

        finishAffinity()
    }
}