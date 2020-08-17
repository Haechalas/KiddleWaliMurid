package com.kiddle.kiddlewalimurid.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.adapter.PengumumanAdapter
import com.kiddle.kiddlewalimurid.model.Pengumuman
import kotlinx.android.synthetic.main.activity_pengumuman.*

class PengumumanActivity : AppCompatActivity() {

    private val pengumuman: ArrayList<Pengumuman> = arrayListOf()
    private val db:FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengumuman)

        img_back_pengumuman.setOnClickListener {
            onBackPressed()
        }

        showRecylerList(pengumuman)
    }

    private fun showRecylerList(list: ArrayList<Pengumuman>): PengumumanAdapter {
        val adapter = PengumumanAdapter(list) {

        }

        getPagePengumumanList {item: ArrayList<Pengumuman> ->
            pengumuman.addAll(item)
            adapter.notifyDataSetChanged()
            adapter.addItemToList(list)
            rv_pengumuman.layoutManager = LinearLayoutManager(this)
            rv_pengumuman.adapter = adapter
        }

        return adapter
    }

    private fun getPagePengumumanList(callback: (item:ArrayList<Pengumuman>) -> Unit) {
        val listPengumuman: ArrayList<Pengumuman> = arrayListOf()
        db.collection("Pengumuman").addSnapshotListener { value, error ->
            if(error != null) return@addSnapshotListener
            for(document in value!!) {
                listPengumuman.add(Pengumuman(
                    document.id,
                    document.getString("judul"),
                    document.getString("isi"),
                    document.getString("tanggal"),
                    document.getString("gambar"),
                    document.getString("video")
                ))
            }
            callback.invoke(listPengumuman)
        }
    }
}