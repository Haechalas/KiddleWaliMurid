package com.kiddle.kiddlewalimurid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.model.pengumuman
import kotlinx.android.synthetic.main.holder_pengumuman.view.*

class PengumumanAdapter(val pengumuman: List<pengumuman>) : RecyclerView.Adapter<PengumumanAdapter.PengumumanViewHolder>()  {

    class PengumumanViewHolder(val view: View) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PengumumanViewHolder {
        return PengumumanViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.holder_pengumuman, parent, false))
    }

    override fun getItemCount() = pengumuman.size

    override fun onBindViewHolder(holder: PengumumanViewHolder, position: Int) {
        val pengumuman = pengumuman[position]
        holder.view.judulPG.text = pengumuman.judul
        holder.view.tanggalPG.text = pengumuman.tanggal
        holder.view.img_PG.setImageResource(pengumuman.img)

    }

}