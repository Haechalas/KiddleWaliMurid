package com.kiddle.kiddlewalimurid.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import com.bumptech.glide.Glide
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.model.Kegiatan
import kotlinx.android.synthetic.main.activity_detail_kegiatan.*

class DetailKegiatanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_kegiatan)

        //mengambil data dari halaman sebelumnya
        val data = intent.getParcelableExtra<Kegiatan>("data")

        if (data != null) {
            tv_judul_detail_kegiatan.text = data.judul
            tv_tanggal_detail_kegiatan.text = data.tanggal
            tv_isi_detail_kegiatan.text=data.isi
            tv_link_detail_kegiatan.text=data.link

            if(!data.gambar.isNullOrEmpty()) {
                img_detail_kegiatan.visibility = View.VISIBLE
                vv_detail_kegiatan.visibility = View.GONE
                Glide.with(this).load(data.gambar).fitCenter().into(img_detail_kegiatan)
            }
            else if(!data.video.isNullOrEmpty()) {
                vv_detail_kegiatan.visibility = View.VISIBLE
                img_detail_kegiatan.visibility = View.GONE
                vv_detail_kegiatan.setVideoURI(Uri.parse(data.video))
                var media_Controller: MediaController = MediaController(this)
                vv_detail_kegiatan.setMediaController(media_Controller)
                media_Controller.setAnchorView(vv_detail_kegiatan)
                vv_detail_kegiatan.seekTo(10)

            }

            if(data.link!="") {
                img_link_detail_kegiatan.visibility = View.VISIBLE
                tv_link_detail_kegiatan.visibility = View.VISIBLE

            }
        }

        //intent untuk kembali ke halaman sebelumnya
        img_back_detail_kegiatan.setOnClickListener {
            onBackPressed()
        }
    }
}