package com.kiddle.kiddlewalimurid.ui

import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.MediaController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kiddleapp.Komentar.KomentarAdapter
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.model.Komentar
import com.kiddle.kiddlewalimurid.model.Pengumuman
import kotlinx.android.synthetic.main.activity_detail_pengumuman.*

class DetailPengumumanActivity : AppCompatActivity() {

    //untuk menyimpan Hasil TugasActivity
    private var komentar = ArrayList<Komentar>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pengumuman)

        val data = intent.getParcelableExtra<Pengumuman>("data")

        img_back_detail_pengumuman.setOnClickListener {
            onBackPressed()
        }

        if (data != null) {
            tv_judul_detail_pengumuman.text = data.judul
            tv_tanggal_detail_pengumuman.text = data.tanggal
            tv_deskripsi_detail_pengumuman.text = data.isi
            if(data.gambar !=0) {
                img_detail_pengumuman.visibility = View.VISIBLE
                vv_detail_pengumuman.visibility = View.GONE
                img_detail_pengumuman.setImageResource(data.gambar)
            } else if(data.video != 0){
                vv_detail_pengumuman.visibility = View.VISIBLE
                img_detail_pengumuman.visibility = View.GONE
                vv_detail_pengumuman.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + data.video))
                var media_Controller: MediaController = MediaController(this)
                vv_detail_pengumuman.setMediaController(media_Controller)
                media_Controller.setAnchorView(vv_detail_pengumuman)
                vv_detail_pengumuman.seekTo(10)
            }
        }

        //recyclerView hasil tugas
        rv_komentar.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        //mengkosongkan isi arraylist
        komentar.clear()

        //bisa diganti dengan data dari firebase
        val temp = Komentar( R.drawable.image_user, "Lidya","Kepala Sekolah","jaga kesehatan")
        komentar.add(temp)

        val temp2 = Komentar(R.drawable.image_user,"Lidya","","biaya SPP bagaimana ?")
        komentar.add(temp2)

        rv_komentar.adapter = KomentarAdapter(komentar) {

        }

        //komentar
        btn_komentar.setOnClickListener {v ->
            val temp = Komentar(R.drawable.image_user,"Lidya","",tv_komentar.text.toString())
            komentar.add(komentar.size, temp)
            rv_komentar.adapter = KomentarAdapter(komentar) {
            }
            tv_komentar.setText("")
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}