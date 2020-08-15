package com.kiddle.kiddlewalimurid.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.ui.*
import com.kiddle.kiddlewalimurid.adapter.PengumumanAdapter
import com.kiddle.kiddlewalimurid.model.Pengumuman
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_beranda.*
import kotlinx.android.synthetic.main.fragment_beranda.view.*

class BerandaFragment : Fragment() {
    private val db:FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var data:Pengumuman

    override fun onStart() {
        super.onStart()

        sharedPreferences = activity?.getSharedPreferences("KIDDLE", Context.MODE_PRIVATE)!!

        nama_murid.text = sharedPreferences.getString("nama", "")
        Glide.with(this).load(sharedPreferences.getString("avatar", "")).centerCrop().into(img_avatar)

        db.collection("Pengumuman").orderBy("tanggal").limit(1).addSnapshotListener { value, error ->
            if(error != null) return@addSnapshotListener
            for(document in value!!) {
                Glide.with(this).load(document.getString("gambar")).fitCenter().into(img_pengumuman_beranda)
                tv_judul_pengumuman_beranda.text = document.getString("judul")
                tv_tanggal_pengumuman_beranda.text = document.getString("tanggal")

                data = Pengumuman(document.getString("judul"),
                    document.getString("isi"),
                    document.getString("tanggal"),
                    document.getString("gambar"),
                    document.getString("video"))
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_beranda, container, false)

        view.card_pengumuman.setOnClickListener {
            startActivity(Intent(activity, DetailPengumumanActivity::class.java).putExtra("data", data))
        }

        view.ic_notification.setOnClickListener {
            Toast.makeText(activity, "Notifikasi", Toast.LENGTH_SHORT).show()
        }

        view.tv_lihat_semua.setOnClickListener {
            startActivity(Intent(activity, PengumumanActivity::class.java))
        }

        view.presensi_card.setOnClickListener {
            startActivity(Intent(activity, PresensiActivity::class.java))
        }

        view.tugas_card.setOnClickListener {
            startActivity(Intent(activity, TugasActivity::class.java))
        }

        view.spp_card.setOnClickListener {
            activity?.bottom_navigation?.selectedItemId = R.id.menu_pembayaran
        }

        view.img_avatar.setOnClickListener {
            activity?.bottom_navigation?.selectedItemId = R.id.menu_profil
        }

        view.ic_notification.setOnClickListener {
            startActivity(Intent(activity, NotifikasiActivity::class.java))
        }

        view.sekolah_profil_card.setOnClickListener {
            startActivity(Intent(activity, ProfilSekolahActivity::class.java))
        }

        view.guru_card.setOnClickListener {
            startActivity(Intent(activity, GuruActivity::class.java))
        }

        view.kegiatan_card.setOnClickListener {
            startActivity(Intent(activity, KegiatanActivity::class.java).putExtra("jenis", "KEGIATAN"))
        }

        view.materi_card.setOnClickListener {
            startActivity(Intent(activity, KegiatanActivity::class.java).putExtra("jenis", "MATERI"))
        }

        view.parent_card.setOnClickListener {
            startActivity(Intent(activity, KegiatanActivity::class.java).putExtra("jenis", "PARENTING"))
        }

        return view
    }

}