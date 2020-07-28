package com.kiddle.kiddlewalimurid.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.adapter.NotifikasiAdapter
import com.kiddle.kiddlewalimurid.model.Notifikasi
import kotlinx.android.synthetic.main.activity_notifikasi.*

class NotifikasiActivity : AppCompatActivity() {

    //untuk menyimpan notifikasi
    private var notifikasi = ArrayList<Notifikasi>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifikasi)

        //recyclerView dengan linear layout
        rv_notifikasi.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        //mengkosongkan isi arraylist
        notifikasi.clear()

        //bisa diganti dengan data dari firebase
        val notif = Notifikasi("Pembelajaran Jarak Dekat",R.drawable.notif, "8 Juli 2020, 10.00 WIB", "Penguman")
        notifikasi.add(notif)

        //agar notifikasi dapat di-click sekaligus mengisi adapter dengan data di arraylist tadi
        rv_notifikasi.adapter =  NotifikasiAdapter(notifikasi) {
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
        }

        //intent untuk kembali ke halaman sebelumnya
        img_back_notifikasi.setOnClickListener {
            onBackPressed()
        }
    }
}