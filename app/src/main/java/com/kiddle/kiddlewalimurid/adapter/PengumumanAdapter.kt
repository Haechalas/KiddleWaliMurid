package com.kiddle.kiddlewalimurid.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.model.Pengumuman
import com.kiddle.kiddlewalimurid.ui.DetailPengumumanActivity

class PengumumanAdapter(private var data: List<Pengumuman>, private val listener: (Pengumuman) -> Unit) : RecyclerView.Adapter<PengumumanAdapter.ViewHolder>()  {

    lateinit var contextAdapter:Context
    private val listPengumuman = ArrayList<Pengumuman>()

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val tv_judul_pengumuman:TextView = view.findViewById(R.id.tv_judul_pengumuman)
        private val tv_tanggal_pengumuman:TextView = view.findViewById(R.id.tv_tanggal_pengumuman)
        private val img_pengumuman:ImageView = view.findViewById(R.id.img_pengumuman)

        fun bindItem(data: Pengumuman, listener: (Pengumuman) -> Unit, context: Context, position: Int) {
            tv_judul_pengumuman.text = data.judul
            tv_tanggal_pengumuman.text = data.tanggal
            Glide.with(context).load(data.gambar).into(img_pengumuman)

            itemView.setOnClickListener {
                listener(data)
            }

            listener.invoke(data)
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
        holder.itemView.setOnClickListener {
            it.context.startActivity(Intent(it.context, DetailPengumumanActivity::class.java).putExtra("data", data[position]))
        }
    }

    fun addItemToList(list: ArrayList<Pengumuman>) {
        listPengumuman.clear()
        listPengumuman.addAll(list)
    }

}