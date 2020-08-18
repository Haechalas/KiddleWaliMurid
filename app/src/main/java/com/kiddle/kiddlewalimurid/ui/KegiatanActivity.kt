package com.kiddle.kiddlewalimurid.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.adapter.KegiatanAdapter
import com.kiddle.kiddlewalimurid.model.Kegiatan
import kotlinx.android.synthetic.main.activity_kegiatan.*

class KegiatanActivity : AppCompatActivity() {

    //untuk menyimpan Kegiatan
    private var model: ArrayList<Kegiatan> = arrayListOf()
    private val db:FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kegiatan)

        when {
            intent.getStringExtra("jenis") == "Kegiatan" -> {
                tv_jenis_fungsi.setText(R.string.kegiatan)
                gambar_jenis_fungsi.setImageResource(R.drawable.ilustrasi_kegiatan)

            }
            intent.getStringExtra("jenis") == "Parenting" -> {
                tv_jenis_fungsi.setText(R.string.parenting)
                gambar_jenis_fungsi.setImageResource(R.drawable.ilustrasi_parenting)

            }
            intent.getStringExtra("jenis") == "Materi" -> {
                tv_jenis_fungsi.setText(R.string.materi)
                gambar_jenis_fungsi.setImageResource(R.drawable.ilustrasi_materi)

            }
        }

        showRecylerList(model)

        //intent untuk kembali ke halaman sebelumnya
        img_back_kegiatan.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showRecylerList(list: ArrayList<Kegiatan>): KegiatanAdapter {
        val adapter = KegiatanAdapter(list) {

        }

        getPageKegiatanList {item: ArrayList<Kegiatan> ->
            model.addAll(item)
            adapter.notifyDataSetChanged()
            adapter.addItemToList(list)
            rv_jenis_fungsi.layoutManager = LinearLayoutManager(this)
            rv_jenis_fungsi.adapter = adapter
        }

        return adapter
    }

    private fun getPageKegiatanList(callback: (item: ArrayList<Kegiatan>) -> Unit) {
        val listKegiatan: ArrayList<Kegiatan> = arrayListOf()
        db.collection(intent.getStringExtra("jenis").toString()).addSnapshotListener { value, error ->
            if(error != null) return@addSnapshotListener
            for(document in value!!) {
                listKegiatan.add(
                    Kegiatan(
                        document.getString("judul"),
                        document.getString("isi"),
                        document.getString("tanggal"),
                        document.getString("gambar"),
                        document.getString("video"),
                        document.getString("link")
                    )
                )
            }
            callback.invoke(listKegiatan)
        }
    }
}