package com.kiddle.kiddlewalimurid.UI

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.model.Tugas
import kotlinx.android.synthetic.main.activity_detail_tugas.*
import java.io.File


class DetailTugasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tugas)

        //mengambil data dari halaman sebelumnya
        val data = intent.getParcelableExtra<Tugas>("data")

        if (data != null) {
            tv_judul_detail_tugas.text = data.judul
            tv_tanggal_detail_tugas.text = data.tanggal
            tv_waktu_detail_tugas.text = data.jam
            tv_link_detail_tugas.text=data.link

            if(data.gambar!=0) {
                img_detail_tugas.visibility = View.VISIBLE
                vv_detail_tugas.visibility = View.GONE
                img_detail_tugas.setImageResource(data.gambar)
            }
            else if(data.video!=0) {

                vv_detail_tugas.visibility = View.VISIBLE
                img_detail_tugas.visibility = View.GONE
                vv_detail_tugas.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + data.video))
                var media_Controller: MediaController = MediaController(this)
                vv_detail_tugas.setMediaController(media_Controller)
                media_Controller.setAnchorView(vv_detail_tugas)
                vv_detail_tugas.seekTo(10)

            }

            if(data.link!="") {
                img_link_detail_tugas.visibility = View.VISIBLE
                tv_link_detail_tugas.visibility = View.VISIBLE
            }
        }

        img_back_detail_tugas.setOnClickListener {
            onBackPressed()
        }

        btn_unggah.setOnClickListener {
            startActivityForResult(Intent(Intent.ACTION_GET_CONTENT).setType("*/*"), 1)
        }

        btn_kumpulkan.setOnClickListener {
            Toast.makeText(this, "Kumpulkan", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 1 && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            var uri:Uri = data.data!!
            var uriString = uri.toString()
            var file:File = File(uriString)
            var path = file.absolutePath
            var displayName = ""

            if(uriString.startsWith("content://")) {
                var cursor: Cursor? = null
                try {
                    cursor = applicationContext.contentResolver.query(uri, null, null, null, null)
                    if (cursor != null && cursor.moveToFirst()) {
                        displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                    }
                } finally {
                    cursor!!.close()
                }
            } else if(uriString.startsWith("file://")) {
                displayName = file.name
            }

            tv_hasil_tugas.text = displayName
        }

        super.onActivityResult(requestCode, resultCode, data)

    }
}