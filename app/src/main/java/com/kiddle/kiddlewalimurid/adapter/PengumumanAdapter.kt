package com.kiddle.kiddlewalimurid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.model.Pengumuman

class PengumumanAdapter(val data: List<Pengumuman>, private val listener: (Pengumuman) -> Unit) : RecyclerView.Adapter<PengumumanAdapter.ViewHolder>()  {

    lateinit var contextAdapter:Context

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val tv_judul_pengumuman:TextView = view.findViewById(R.id.tv_judul_pengumuman)
        private val tv_tanggal_pengumuman:TextView = view.findViewById(R.id.tv_tanggal_pengumuman)
        private val img_pengumuman:ImageView = view.findViewById(R.id.img_pengumuman)

        fun bindItem(pengumuman: Pengumuman, listener: (Pengumuman) -> Unit, context: Context, position: Int) {
            tv_judul_pengumuman.text = pengumuman.judul
            tv_tanggal_pengumuman.text = pengumuman.tanggal
            img_pengumuman.setImageResource(pengumuman.gambar)

            itemView.setOnClickListener {
                listener(pengumuman)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PengumumanAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView: View = layoutInflater.inflate(R.layout.holder_pengumuman, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter, position)
    }

}