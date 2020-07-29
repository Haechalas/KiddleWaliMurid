package com.kiddle.kiddlewalimurid.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.adapter.GuruAdapter
import com.kiddle.kiddlewalimurid.model.Guru
import kotlinx.android.synthetic.main.activity_guru.*
import kotlinx.android.synthetic.main.holder_guru.*

class GuruActivity : AppCompatActivity() {

    //untuk menyimpan guru
    private var guru = ArrayList<Guru>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guru)

        //recyclerView dengan linear layout
        rv_guru.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        //mengkosongkan arraylist
        guru.clear()

        //bisa diganti dengan data dari firebase
        val item = Guru(R.drawable.image_user,"Lee Ji Eun","175150201","085779993333","Bintang Kecil","punten123")
        guru.add(item)

        //agar list guru dapat di-click sekaligus mengisi adapter dengan data di arraylist
        rv_guru.adapter = GuruAdapter(guru) {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + tv_kontak_guru.text)
            startActivity(intent)
        }

        //intent untuk kembali ke halaman sebelumnya
        img_back_guru.setOnClickListener {
            onBackPressed()
        }

    }
}