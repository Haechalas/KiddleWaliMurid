package com.kiddle.kiddlewalimurid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.adapter.PengumumanAdapter
import com.kiddle.kiddlewalimurid.model.jurnal
import com.kiddle.kiddlewalimurid.model.pengumuman
import kotlinx.android.synthetic.main.fragment_beranda.view.*
import java.util.ArrayList

class BerandaFragment : Fragment() {

    private var pengumuman = ArrayList<jurnal>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_beranda, container, false)

        val  pengumuman = listOf(
            pengumuman("Belajar jarak jauh", "30 maret", R.drawable.image_detail_pengumuman),
            pengumuman("Menghitung", "30 maret", R.drawable.image_detail_kegiatan),
            pengumuman("Menghitung", "30 maret", R.drawable.image_detail_materi)

        )
        //RV
        view.RecyclerViewPG.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        //Adapter
        view.RecyclerViewPG.adapter = PengumumanAdapter(pengumuman)
        return view


    }
}