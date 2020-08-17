package com.kiddle.kiddlewalimurid.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.model.Tugas
import kotlinx.android.synthetic.main.activity_detail_tugas.*
import java.io.File
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*


class DetailTugasActivity : AppCompatActivity() {

    lateinit var file_location: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tugas)

        //mengambil data dari halaman sebelumnya
        val data = intent.getParcelableExtra<Tugas>("data")
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        var storage: StorageReference
        val sharedPreferences = getSharedPreferences("KIDDLE", Context.MODE_PRIVATE)
        var flag:Boolean = false

        db.document("Hasil Tugas/${data?.id}/Isi Tugas/${sharedPreferences.getString("id_murid", "")}").get().addOnSuccessListener {
            if(it.getString("nama") != null) {
                tv_hasil_tugas.text = it.getString("tugas")
            }
        }

        if (data != null) {
            tv_judul_detail_tugas.text = data.judul
            tv_tanggal_detail_tugas.text = data.tanggal
            tv_waktu_detail_tugas.text = data.jam
            tv_link_detail_tugas.text=data.link

            if(!data.gambar.isNullOrEmpty()) {
                img_detail_tugas.visibility = View.VISIBLE
                vv_detail_tugas.visibility = View.GONE
                Glide.with(this).load(data.gambar).centerCrop().into(img_detail_tugas)
            }
            else if(!data.video.isNullOrEmpty()) {
                vv_detail_tugas.visibility = View.VISIBLE
                img_detail_tugas.visibility = View.GONE
                vv_detail_tugas.setVideoURI(Uri.parse(data.video))
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
            flag = true
            startActivityForResult(Intent(Intent.ACTION_GET_CONTENT).setType("*/*"), 1)
        }

        btn_kumpulkan.setOnClickListener {
            if(flag && file_location != null) {
                btn_unggah.isEnabled = false
                btn_kumpulkan.isEnabled = false
                btn_kumpulkan.text = "Loading"

                var builder = StringBuilder()
                builder.append(SimpleDateFormat("dd MMMM", Locale.getDefault()).format(Date())).append(", ").append(SimpleDateFormat("HH.mm", Locale.getDefault()).format(Date())).append(" WIB")

                storage = FirebaseStorage.getInstance().reference.child("Hasil Tugas").child("${data?.id}").child(tv_hasil_tugas.text.toString())
                storage.putFile(file_location).addOnSuccessListener {
                    storage.downloadUrl.addOnSuccessListener {
                        db.document("Hasil Tugas/${data?.id}/Isi Tugas/${sharedPreferences.getString("id_murid", "")}").set(
                            mapOf("avatar" to sharedPreferences.getString("avatar", ""), "nama" to sharedPreferences.getString("nama", ""), "waktu" to builder.toString(), "file" to it.toString(), "tugas" to tv_hasil_tugas.text.toString())
                        ).addOnCompleteListener {
                            onBackPressed()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Harap pilih tugas yang ingin di-upload!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 1 && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            file_location = data.data!!
            var uriString = file_location.toString()
            var file:File = File(uriString)
            var path = file.absolutePath
            var displayName = ""

            if(uriString.startsWith("content://")) {
                var cursor: Cursor? = null
                try {
                    cursor = applicationContext.contentResolver.query(file_location, null, null, null, null)
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