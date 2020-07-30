package com.kiddle.kiddlewalimurid.adapter

import android.content.Context
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

class KegiatanAdapter(private var data: List<Kegiatan>, private val listener:(Kegiatan)-> Unit):RecyclerView.Adapter<KegiatanAdapter.ViewHolder>() {

    lateinit var contextAdapter: Context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tv_judul: TextView = view.findViewById(R.id.tv_judul_list_kegiatan)
        val img_kegiatan: ImageView = view.findViewById(R.id.img_banner_list_kegiatan)
        val vv_kegiatan: VideoView = view.findViewById(R.id.vv_banner_list_kegiatan)

        fun bindItem(data: Kegiatan,listener: (Kegiatan) -> Unit, context: Context,position: Int) {
            tv_judul.text = data.judul

            if (data.gambar != 0) {
                img_kegiatan.visibility = View.VISIBLE
                vv_kegiatan.visibility = View.GONE
                img_kegiatan.setImageResource(data.gambar)
            } else if (data.video != 0) {
                img_kegiatan.visibility = View.VISIBLE
                vv_kegiatan.visibility = View.GONE
                vv_kegiatan.setVideoURI(Uri.parse("android.resource://" + context.packageName + "/" + data.video))
                vv_kegiatan.seekTo(10)
            }

            itemView.setOnClickListener {
                listener(data)
            }

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
        // biar gambarnya ada radiusnya
        if(data[position].gambar!=0){
            val url: Int = data[position].gambar
            Glide.with(contextAdapter).load(url).transform(CenterCrop(),RoundedCorners(32)).into(holder.img_kegiatan)

        }else if(data[position].video!=0){
            val url2: Int = data[position].video
            Glide.with(contextAdapter).load(Uri.parse("android.resource://" + contextAdapter.packageName + "/" + url2)).transform(CenterCrop(),RoundedCorners(32)).into(holder.img_kegiatan)
        }
    }
}