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
import com.kiddle.kiddlewalimurid.ui.DetailJurnalActivity
import com.kiddle.kiddlewalimurid.adapter.JurnalAdapter
import com.kiddle.kiddlewalimurid.model.Jurnal
import kotlinx.android.synthetic.main.fragment_jurnal.*
import kotlinx.android.synthetic.main.fragment_jurnal.view.*
import java.util.*
import kotlin.collections.ArrayList

class JurnalFragment : Fragment()  {

    //untuk menyimpan JurnalActivity
    private var jurnal: ArrayList<Jurnal> = arrayListOf()
    private val db:FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_jurnal, container, false)

        sharedPreferences = activity?.getSharedPreferences("KIDDLE", Context.MODE_PRIVATE)!!

        jurnal.clear()
        showRecylerList(jurnal)

        return view
    }

    private fun showRecylerList(list: ArrayList<Jurnal>): JurnalAdapter {
        val adapter = JurnalAdapter(list) {

        }

        getPageJurnalList {item: ArrayList<Jurnal> ->
            jurnal.addAll(item)
            adapter.notifyDataSetChanged()
            adapter.addItemToList(list)
            rv_jurnal.layoutManager = LinearLayoutManager(activity)
            rv_jurnal.adapter = adapter
        }

        return adapter
    }

    private fun getPageJurnalList(callback: (item: ArrayList<Jurnal>) -> Unit) {
        val listJurnal: ArrayList<Jurnal> = arrayListOf()
        db.collection("Jurnal").whereEqualTo("kelas", "${sharedPreferences.getString("kelas", "")}").addSnapshotListener { value, error ->
            if(error != null) return@addSnapshotListener
            for(document in value!!) {
                listJurnal.add(
                    Jurnal(
                        document.getString("jenis"),
                        document.getString("kelas"),
                        document.getString("judul"),
                        document.getString("isi"),
                        document.getString("tanggal"),
                        document.getString("gambar"),
                        document.getString("video")
                    )
                )
            }
            callback.invoke(listJurnal)
        }
    }

}