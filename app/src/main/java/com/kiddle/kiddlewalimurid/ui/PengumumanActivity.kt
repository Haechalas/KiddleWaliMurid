package com.kiddle.kiddlewalimurid.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.adapter.PengumumanAdapter
import com.kiddle.kiddlewalimurid.model.Pengumuman
import kotlinx.android.synthetic.main.activity_pengumuman.*

class PengumumanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengumuman)

        img_back_pengumuman.setOnClickListener {
            onBackPressed()
        }
    }
}