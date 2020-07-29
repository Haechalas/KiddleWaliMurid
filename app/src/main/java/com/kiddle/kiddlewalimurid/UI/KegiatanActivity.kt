package com.kiddle.kiddlewalimurid.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.adapter.KegiatanAdapter
import com.kiddle.kiddlewalimurid.model.Kegiatan
import kotlinx.android.synthetic.main.activity_kegiatan.*

class KegiatanActivity : AppCompatActivity() {

    //untuk menyimpan Kegiatan
    private var model = ArrayList<Kegiatan>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kegiatan)

        if(intent.getStringExtra("jenis") == "KEGIATAN") {
            rv_jenis_fungsi.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            tv_jenis_fungsi.setText(R.string.kegiatan)
            gambar_jenis_fungsi.setImageResource(R.drawable.image_kegiatan)
            //mengkosongkan isi arraylist
            model.clear()

            //bisa diganti dengan data dari firebase
            val temp = Kegiatan("Hari Ibu","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vitae tellus feugiat, efficitur lacus nec, maximus felis. Maecenas ultrices tempor enim, et malesuada nisl lacinia eget", "20 Juli 2020", R.drawable.image_detail_kegiatan,0, "")
            model.add(temp)
        } else if(intent.getStringExtra("jenis") == "PARENTING") {
            rv_jenis_fungsi.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            tv_jenis_fungsi.setText(R.string.parenting)
            gambar_jenis_fungsi.setImageResource(R.drawable.image_parenting)

            //mengkosongkan isi arraylist
            model.clear()

            val temp2 = Kegiatan("Hari Ibu",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vitae tellus feugiat, efficitur lacus nec, maximus felis. Maecenas ultrices tempor enim, et malesuada nisl lacinia eget",
                "20 Juli 2020",
                R.drawable.image_detail_pengumuman, 0, "https://youtu.be/g9aXIpJFKyU")
            model.add(temp2)
        } else if(intent.getStringExtra("jenis") == "MATERI") {
            rv_jenis_fungsi.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            tv_jenis_fungsi.setText(R.string.materi)
            gambar_jenis_fungsi.setImageResource(R.drawable.image_materi)

            //mengkosongkan isi arraylist
            model.clear()

            val temp3 = Kegiatan("Hari Ibu", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vitae tellus feugiat, efficitur lacus nec, maximus felis. Maecenas ultrices tempor enim, et malesuada nisl lacinia eget", "20 Juli 2020", R.drawable.image_detail_materi, 0, "https://youtu.be/g9aXIpJFKyU")
            model.add(temp3)
        }

        rv_jenis_fungsi.adapter = KegiatanAdapter(model) {
            val intent: Intent =  Intent(this@KegiatanActivity, DetailKegiatanActivity::class.java).putExtra("data", it)
            startActivity(intent)
        }

        //intent untuk kembali ke halaman sebelumnya
        img_back_kegiatan.setOnClickListener {
            onBackPressed()
        }
    }
}