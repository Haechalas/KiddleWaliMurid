package com.kiddle.kiddlewalimurid.UI

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.model.Jurnal
import kotlinx.android.synthetic.main.activity_detail_jurnal.*

class DetailJurnalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_jurnal)

        //mengambil data dari halaman sebelumnya
        val data = intent.getParcelableExtra<Jurnal>("data")

        if (data != null) {
            tv_jenis_detail_jurnal.text = data.jenis
            tv_kelas_detail_jurnal.text = data.kelas
            tv_judul_detail_jurnal.text = data.judul
            tv_tanggal_detail_jurnal.text=data.tanggal
            tv_isi_detail_jurnal.text=data.isi

            if(data.gambar!=0) {
                img_detail_jurnal.visibility = View.VISIBLE
                vv_detail_jurnal.visibility = View.GONE
                img_detail_jurnal.setImageResource(data.gambar)
            }
            else if(data.video!=0) {
                vv_detail_jurnal.visibility = View.VISIBLE
                img_detail_jurnal.visibility = View.GONE
                vv_detail_jurnal.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + data.video))
                var media_Controller: MediaController = MediaController(this)
                vv_detail_jurnal.setMediaController(media_Controller)
                media_Controller.setAnchorView(vv_detail_jurnal)
                vv_detail_jurnal.seekTo(10)

            }
        }

        //intent untuk kembali ke halaman sebelumnya
        img_back_detail_jurnal.setOnClickListener {
            onBackPressed()
        }

    }
}