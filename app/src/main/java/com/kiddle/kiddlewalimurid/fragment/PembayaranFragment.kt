package com.kiddle.kiddlewalimurid.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.ui.CaraPembayaranActivity
import com.kiddle.kiddlewalimurid.ui.KonfirmasiPembayaranActivity
import com.kiddle.kiddlewalimurid.adapter.ItemBayarSppAdapter
import com.kiddle.kiddlewalimurid.model.ItemBayarSpp
import kotlinx.android.synthetic.main.fragment_pembayaran.view.*

class PembayaranFragment : Fragment() {

    //untuk menyimpan murid
    private var spp = ArrayList<ItemBayarSpp>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pembayaran, container, false)

        //recyclerView spp
        view.rv_pembayaran.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        //mengkosongkan isi arraylist
        spp.clear()

        val temp = ItemBayarSpp("19990305", "Juli 2020", "Lunas", R.drawable.image_detail_materi)
        spp.add(temp)

        //dari database pembayaran terbaru dan masukin ke textView
        val recent = ItemBayarSpp("19990305", "Juli 2020", "Lunas", 0)

        view.rv_pembayaran.adapter =
            ItemBayarSppAdapter(spp) {
                startActivity(Intent(activity, KonfirmasiPembayaranActivity::class.java).putExtra("data", it))
            }

        view.btn_cara_bayar.setOnClickListener {
            startActivity(Intent(activity, CaraPembayaranActivity::class.java))
        }

        view.btn_simpan_bukti.setOnClickListener {
            startActivity(Intent(activity, KonfirmasiPembayaranActivity::class.java).putExtra("data", recent))
        }

        return view
    }

}