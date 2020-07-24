package com.kiddle.kiddlewalimurid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.model.jurnal
import kotlinx.android.synthetic.main.holder_jurnal.view.*

class JurnalAdapter(val jurnalanak: List<jurnal>) : RecyclerView.Adapter<JurnalAdapter.JurnalViewHolder>()  {

    class JurnalViewHolder(val view:View) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JurnalViewHolder {
    return JurnalViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.holder_jurnal, parent, false))
    }

    override fun getItemCount() = jurnalanak.size

    override fun onBindViewHolder(holder: JurnalViewHolder, position: Int) {
    val jurnal = jurnalanak[position]
        holder.view.Viewjudul.text = jurnal.judul
        holder.view.Viewtanggal.text = jurnal.tanggal
        holder.view.Viewbidang.text = jurnal.bidang

    }

}