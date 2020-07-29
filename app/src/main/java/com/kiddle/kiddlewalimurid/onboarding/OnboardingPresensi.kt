package com.kiddle.kiddlewalimurid.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.ui.LoginActivity
import kotlinx.android.synthetic.main.onboarding_jurnal.*
import kotlinx.android.synthetic.main.onboarding_presensi.*

class OnboardingPresensi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding_presensi)

        val top_down: Animation = AnimationUtils.loadAnimation(this, R.anim.top_down)
        val bottom_up: Animation = AnimationUtils.loadAnimation(this, R.anim.bottom_up)

        img_ob_presensi.startAnimation(top_down)
        tv_title_presensi.startAnimation(top_down)
        tv_subtitle_presensi.startAnimation(top_down)

        btn_next_presensi.startAnimation(bottom_up)
        btn_skip_presensi.startAnimation(bottom_up)
        indikator_presensi.startAnimation(bottom_up)

        btn_next_presensi.setOnClickListener {
            startActivity(Intent(this, OnboardingJurnal::class.java))
            finishAffinity()
        }

        btn_skip_presensi.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finishAffinity()
        }

    }
}