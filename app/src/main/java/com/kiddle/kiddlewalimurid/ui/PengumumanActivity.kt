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

        val  pengumuman = listOf(
            Pengumuman("Belajar jarak jauh","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vitae tellus feugiat, efficitur lacus nec, maximus felis. Maecenas ultrices tempor enim, et malesuada nisl lacinia eget" ,"30 maret", R.drawable.image_detail_pengumuman, 0),
            Pengumuman("Menghitung", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vitae tellus feugiat, efficitur lacus nec, maximus felis. Maecenas ultrices tempor enim, et malesuada nisl lacinia eget","30 maret", R.drawable.image_detail_kegiatan, 0)
           )
        //RV
        rv_pengumuman.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //Adapter
        rv_pengumuman.adapter = PengumumanAdapter(pengumuman){
            val intent = Intent(this@PengumumanActivity, DetailPengumumanActivity::class.java).putExtra("data", it)
            startActivity(intent)
        }

        img_back_pengumuman.setOnClickListener {
            onBackPressed()
        }
    }
}