package com.kiddle.kiddlewalimurid.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.model.Kegiatan
import com.kiddle.kiddlewalimurid.ui.DetailKegiatanActivity

class KegiatanAdapter(private var data: List<Kegiatan>, private val listener:(Kegiatan)-> Unit):RecyclerView.Adapter<KegiatanAdapter.ViewHolder>() {

    lateinit var contextAdapter: Context
    private val listKegiatan = ArrayList<Kegiatan>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tv_judul: TextView = view.findViewById(R.id.tv_judul_list_kegiatan)
        val img_kegiatan: ImageView = view.findViewById(R.id.img_banner_list_kegiatan)
        val vv_kegiatan: VideoView = view.findViewById(R.id.vv_banner_list_kegiatan)

        fun bindItem(data: Kegiatan,listener: (Kegiatan) -> Unit, context: Context,position: Int) {
            tv_judul.text = data.judul

            if (!data.gambar.isNullOrEmpty()) {
                img_kegiatan.visibility = View.VISIBLE
                vv_kegiatan.visibility = View.GONE
                Glide.with(context).load(data.gambar).fitCenter().into(img_kegiatan)
            } else if (!data.video.isNullOrEmpty()) {
                img_kegiatan.visibility = View.VISIBLE
                vv_kegiatan.visibility = View.GONE
                vv_kegiatan.setVideoURI(Uri.parse(data.video))
                vv_kegiatan.seekTo(10)
            }

            itemView.setOnClickListener {
                listener(data)
            }

            listener.invoke(data)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView: View = layoutInflater.inflate(R.layout.holder_kegiatan, parent, false)
        return ViewHolder(
            inflatedView
        )

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter, position)
        if(!data[position].gambar.isNullOrEmpty()){
            Glide.with(contextAdapter).load(data[position].gambar).transform(CenterCrop(),RoundedCorners(32)).into(holder.img_kegiatan)

        }else if(!data[position].video.isNullOrEmpty()){
            Glide.with(contextAdapter).load(Uri.parse(data[position].video)).transform(CenterCrop(),RoundedCorners(32)).into(holder.img_kegiatan)
        }

        holder.itemView.setOnClickListener {
            it.context.startActivity(Intent(it.context, DetailKegiatanActivity::class.java).putExtra("data", data[position]))
        }
    }

    fun addItemToList(list: ArrayList<Kegiatan>) {
        listKegiatan.clear()
        listKegiatan.addAll(list)
    }
}