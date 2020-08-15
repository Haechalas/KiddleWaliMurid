package com.kiddle.kiddlewalimurid.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.adapter.TugasAdapter
import com.kiddle.kiddlewalimurid.model.Tugas
import kotlinx.android.synthetic.main.activity_tugas.*

class TugasActivity : AppCompatActivity() {

    //untuk menyimpan TugasActivity
    private val tugas: ArrayList<Tugas> = arrayListOf()
    private val db:FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var sharedPreferences:SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tugas)

        sharedPreferences = getSharedPreferences("KIDDLE", Context.MODE_PRIVATE)

        img_back_tugas.setOnClickListener {
            onBackPressed()
        }

        showRecyclerList(tugas)

    }

    private fun showRecyclerList(list: ArrayList<Tugas>): TugasAdapter {
        val adapter = TugasAdapter(list) {

        }

        getPageTugasList {item: ArrayList<Tugas> ->
            tugas.addAll(item)
            adapter.notifyDataSetChanged()
            adapter.addItemToList(list)
            rv_tugas.layoutManager = LinearLayoutManager(this)
            rv_tugas.adapter = adapter
        }

        return adapter

    }

    private fun getPageTugasList(callback: (item: ArrayList<Tugas>) -> Unit) {
        val listTugas: ArrayList<Tugas> = arrayListOf()
        db.collection("Tugas").whereEqualTo("kelas", "${sharedPreferences.getString("kelas", "")}").addSnapshotListener { value, error ->
            if (error != null) return@addSnapshotListener
            for(document in value!!) {
                listTugas.add(Tugas(
                    document.id,
                    document.getString("kelas"),
                    document.getString("judul"),
                    document.getString("isi"),
                    document.getString("tanggal"),
                    document.getString("jam"),
                    document.getString("jumlah"),
                    document.getString("gambar"),
                    document.getString("video"),
                    document.getString("link")
                ))
            }
            callback.invoke(listTugas)
        }
    }
}
