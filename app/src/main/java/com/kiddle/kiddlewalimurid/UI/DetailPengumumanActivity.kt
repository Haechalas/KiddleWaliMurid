package com.kiddle.kiddlewalimurid.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.model.Pengumuman
import kotlinx.android.synthetic.main.activity_detail_pengumuman.*

class DetailPengumumanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pengumuman)

        val data = intent.getParcelableExtra<Pengumuman>("data")

        img_back_detail_pengumuman.setOnClickListener {
            onBackPressed()
        }

        if (data != null) {
            img_detail_pengumuman.setImageResource(data.gambar)
            tv_judul_detail_pengumuman.text = data.judul
            tv_tanggal_detail_pengumuman.text = data.tanggal
            tv_deskripsi_detail_pengumuman.text = data.isi
        }

    }
}