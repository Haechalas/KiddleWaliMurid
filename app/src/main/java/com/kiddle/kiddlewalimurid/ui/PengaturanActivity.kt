package com.kiddle.kiddlewalimurid.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kiddle.kiddlewalimurid.R
import kotlinx.android.synthetic.main.activity_pengaturan.*

class PengaturanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengaturan)

        val sharedPreferences = getSharedPreferences("KIDDLE", Context.MODE_PRIVATE)

        img_back_pengaturan.setOnClickListener {
            onBackPressed()
        }

        tv_logout.setOnClickListener {
            sharedPreferences.edit().putString("id_murid", null).apply()
            startActivity(Intent(this, LoginActivity::class.java))
            finishAffinity()
        }
    }
}