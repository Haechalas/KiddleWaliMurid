package com.kiddle.kiddlewalimurid.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.ui.LoginActivity
import com.kiddle.kiddlewalimurid.ui.MainActivity
import kotlinx.android.synthetic.main.onboarding_pembayaran.*
import kotlinx.android.synthetic.main.onboarding_rapor.*

class OnboardingRapor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding_rapor)

        val top_down: Animation = AnimationUtils.loadAnimation(this, R.anim.top_down)
        val bottom_up: Animation = AnimationUtils.loadAnimation(this, R.anim.bottom_up)

        img_ob_rapor.startAnimation(top_down)
        tv_title_rapor.startAnimation(top_down)
        tv_subtitle_rapor.startAnimation(top_down)

        btn_next_rapor.startAnimation(bottom_up)
        btn_skip_rapor.startAnimation(bottom_up)
        indikator_rapor.startAnimation(bottom_up)

        btn_next_rapor.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finishAffinity()
        }
    }
}