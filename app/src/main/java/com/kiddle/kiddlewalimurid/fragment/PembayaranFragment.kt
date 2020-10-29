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
import com.google.firebase.firestore.FirebaseFirestore
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.ui.CaraPembayaranActivity
import com.kiddle.kiddlewalimurid.ui.KonfirmasiPembayaranActivity
import com.kiddle.kiddlewalimurid.adapter.ItemBayarSppAdapter
import com.kiddle.kiddlewalimurid.model.ItemBayarSpp
import kotlinx.android.synthetic.main.fragment_pembayaran.*
import kotlinx.android.synthetic.main.fragment_pembayaran.view.*

class PembayaranFragment : Fragment() {

    private var spp = ArrayList<ItemBayarSpp>()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pembayaran, container, false)
        var deadline:String = ""
        var status:String = "Belum Dibayar"
        var bukti:String = ""

        sharedPreferences = activity?.getSharedPreferences("KIDDLE", Context.MODE_PRIVATE)!!

        db.collection("SPP").limit(1).addSnapshotListener { value, error ->
            if(error != null) return@addSnapshotListener
            for(document in value!!) {
                view.tv_jumlah_pembayaran.text = document.getString("harga")
                view.tv_batas_pembayaran.text = document.getString("batas")
                deadline = document.getString("bulan").toString()
                db.document("Pembayaran Murid/${document.getString("bulan")}/Bukti/${sharedPreferences.getString("id_murid","")}").get().addOnSuccessListener {
                    if(it.exists()){
                        if(it.getString("status") == "Diterima"){
                            view.btn_simpan_bukti.isEnabled = false
                            view.btn_simpan_bukti.text = "Sudah Diterima"
                        } else {
                            status = it.getString("status").toString()
                            bukti = it.getString("bukti").toString()
                        }
                    }
                }
            }
        }

        view.btn_cara_bayar.setOnClickListener {
            startActivity(Intent(activity, CaraPembayaranActivity::class.java))
        }

        view.btn_simpan_bukti.setOnClickListener {
            startActivity(Intent(activity, KonfirmasiPembayaranActivity::class.java)
                .putExtra("bulan", deadline)
                .putExtra("harga", view.tv_jumlah_pembayaran.text.toString())
                .putExtra("status", status)
                .putExtra("bukti", bukti))
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
        db.collection("SPP").addSnapshotListener { value, error ->
            if(error != null) return@addSnapshotListener
            if(value!!.isEmpty) Toast.makeText(activity, "Data tidak ada!", Toast.LENGTH_SHORT).show()
            for(document in value!!) {
                listPembayaran.add(
                    ItemBayarSpp(
                        document.getString("batas"),
                        document.getString("bulan"),
                        document.getString("harga"),
                        document.getString("semester")
                    )
                )
            }
            callback.invoke(listPembayaran)
        }
    }

}