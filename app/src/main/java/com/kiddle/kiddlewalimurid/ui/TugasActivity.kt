package com.kiddle.kiddlewalimurid.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.adapter.TugasAdapter
import com.kiddle.kiddlewalimurid.model.Tugas
import kotlinx.android.synthetic.main.activity_tugas.*

class TugasActivity : AppCompatActivity() {

    //untuk menyimpan TugasActivity
    private var tugas = ArrayList<Tugas>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tugas)

        img_back_tugas.setOnClickListener {
            onBackPressed()
        }

        //recyclerView murid
        rv_tugas.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val temp2 = Tugas("Bintang A","Keterampilan","Kerjakan Soal pada akhir video","20 Juli 2020","20.20 WIB","23.59",R.drawable.image_detail_materi,0,"https://youtu.be/g9aXIpJFKyU")
        tugas.add(temp2)

        //agar murid dapat di-click sekaligus mengisi adapter dengan data di arraylist tadi
        rv_tugas.adapter = TugasAdapter(tugas) {
            val intent: Intent = Intent(this@TugasActivity, DetailTugasActivity::class.java).putExtra("data", it)
            startActivity(intent)
        }
    }
}