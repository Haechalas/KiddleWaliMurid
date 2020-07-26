package com.kiddle.kiddlewalimurid.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.UI.DetailPengumumanActivity
import com.kiddle.kiddlewalimurid.UI.PengumumanActivity
import com.kiddle.kiddlewalimurid.adapter.PengumumanAdapter
import com.kiddle.kiddlewalimurid.model.jurnal
import com.kiddle.kiddlewalimurid.model.Pengumuman
import kotlinx.android.synthetic.main.fragment_beranda.view.*
import java.util.ArrayList

class BerandaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_beranda, container, false)

        val  pengumuman = listOf(
            Pengumuman("Belajar jarak jauh","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vitae tellus feugiat, efficitur lacus nec, maximus felis. Maecenas ultrices tempor enim, et malesuada nisl lacinia eget" ,"30 maret", R.drawable.image_detail_pengumuman, 0),
            Pengumuman("Menghitung", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vitae tellus feugiat, efficitur lacus nec, maximus felis. Maecenas ultrices tempor enim, et malesuada nisl lacinia eget","30 maret", R.drawable.image_detail_kegiatan, 0)
        )
        //RV
        view.rv_pengumuman_beranda.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        //Adapter
        view.rv_pengumuman_beranda.adapter = PengumumanAdapter(pengumuman){
            val intent = Intent(activity, DetailPengumumanActivity::class.java).putExtra("data", it)
            startActivity(intent)
        }

        view.ic_notification.setOnClickListener {
            Toast.makeText(activity, "Notifikasi", Toast.LENGTH_SHORT).show()
        }

        view.tv_lihat_semua.setOnClickListener {
            startActivity(Intent(activity, PengumumanActivity::class.java))
        }

        return view

    }
}