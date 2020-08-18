package com.kiddle.kiddlewalimurid.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.kiddle.kiddlewalimurid.R
import kotlinx.android.synthetic.main.activity_profil_sekolah.*

class ProfilSekolahActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_sekolah)

        val db: FirebaseFirestore = FirebaseFirestore.getInstance()

        db.collection("Profil Sekolah").get().addOnSuccessListener {
            for(snapshot in it.documents) {
                Glide.with(this).load(snapshot.getString("banner")).centerCrop().into(img_profil_sekolah)
                tv_nama_sekolah.text = snapshot.getString("nama")
                tv_alamat_sekolah.text = snapshot.getString("alamat")
                tv_kontak_sekolah.text = snapshot.getString("kontak")
                tv_deskripsi_sekolah.text = snapshot.getString("isi")
                tv_visi_sekolah.text = snapshot.getString("visi")
                tv_misi_sekolah.text = snapshot.getString("misi")
                tv_fasilitas_sekolah.text = snapshot.getString("fasilitas")
                tv_prestasi_sekolah.text = snapshot.getString("prestasi")
            }
        }

        //intent buat manggil telepon
        btn_telepon_sekolah.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + tv_kontak_sekolah.text)
            startActivity(intent)
        }

        img_back_profil_sekolah.setOnClickListener {
            onBackPressed()
        }
    }
}