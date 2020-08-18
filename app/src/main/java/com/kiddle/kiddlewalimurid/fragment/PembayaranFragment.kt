package com.kiddle.kiddlewalimurid.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.ui.CaraPembayaranActivity
import com.kiddle.kiddlewalimurid.ui.KonfirmasiPembayaranActivity
import com.kiddle.kiddlewalimurid.adapter.ItemBayarSppAdapter
import com.kiddle.kiddlewalimurid.model.ItemBayarSpp
import kotlinx.android.synthetic.main.fragment_pembayaran.*
import kotlinx.android.synthetic.main.fragment_pembayaran.view.*

class PembayaranFragment : Fragment() {

    //untuk menyimpan murid
    private var spp = ArrayList<ItemBayarSpp>()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pembayaran, container, false)

        sharedPreferences = activity?.getSharedPreferences("KIDDLE", Context.MODE_PRIVATE)!!

        db.collection("SPP").limit(1).addSnapshotListener { value, error ->
            if(error != null) return@addSnapshotListener
            for(document in value!!) {
                tv_jumlah_pembayaran.text = document.getString("jumlah")
                tv_batas_pembayaran.text = document.getString("batas")
            }
        }

        view.btn_cara_bayar.setOnClickListener {
            startActivity(Intent(activity, CaraPembayaranActivity::class.java))
        }

        view.btn_simpan_bukti.setOnClickListener {
            startActivity(Intent(activity, KonfirmasiPembayaranActivity::class.java)
                .putExtra("bulan", tv_batas_pembayaran.text.toString())
                .putExtra("jumlah", tv_jumlah_pembayaran.text.toString()))
        }

        spp.clear()
        showRecylerList(spp)

        return view
    }

    private fun showRecylerList(list: ArrayList<ItemBayarSpp>): ItemBayarSppAdapter {
        val adapter = ItemBayarSppAdapter(list) {

        }

        getPagePembayaranList {item: ArrayList<ItemBayarSpp> ->
            spp.addAll(item)
            adapter.notifyDataSetChanged()
            adapter.addItemToList(list)
            rv_pembayaran.layoutManager = LinearLayoutManager(activity)
            rv_pembayaran.adapter = adapter
        }

        return adapter
    }

    private fun getPagePembayaranList(callback: (item: ArrayList<ItemBayarSpp>) -> Unit) {
        val listPembayaran: ArrayList<ItemBayarSpp> = arrayListOf()
        db.collection("Pembayaran Murid/${sharedPreferences.getString("kelas", "")}/${sharedPreferences.getString("id_murid", "")}").addSnapshotListener { value, error ->
            if(error != null) return@addSnapshotListener
            for(document in value!!) {
                listPembayaran.add(
                    ItemBayarSpp(
                        document.getString("avatar"),
                        document.getString("bukti"),
                        document.getString("bulan"),
                        document.getString("harga"),
                        document.getString("nama"),
                        document.getString("status"),
                        document.getString("tanggal")
                    )
                )
            }
            callback.invoke(listPembayaran)
        }
    }

}