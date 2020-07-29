package com.kiddle.kiddlewalimurid.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kiddle.kiddlewalimurid.R
import kotlinx.android.synthetic.main.activity_profil_sekolah.*

class ProfilSekolahActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_sekolah)

        //intent buat manggil telepon
        btn_telepon_sekolah.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + tv_kontak_sekolah.text)
            startActivity(intent)
        }

        img_back_profil_sekolah.setOnClickListener {
            onBackPressed()
        }
    }
}