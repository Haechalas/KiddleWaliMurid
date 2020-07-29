package com.kiddle.kiddlewalimurid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kiddle.kiddlewalimurid.R
import kotlinx.android.synthetic.main.activity_pengaturan.*

class PengaturanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengaturan)
        img_back_pengaturan.setOnClickListener {
            onBackPressed()
        }
    }
}