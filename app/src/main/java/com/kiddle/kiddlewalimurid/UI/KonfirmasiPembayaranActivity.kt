package com.kiddle.kiddlewalimurid.UI

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.webkit.MimeTypeMap
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.model.ItemBayarSpp
import kotlinx.android.synthetic.main.activity_edit_profil.*
import kotlinx.android.synthetic.main.activity_konfirmasi_pembayaran.*

class KonfirmasiPembayaranActivity : AppCompatActivity() {

    lateinit var img_location: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konfirmasi_pembayaran)

        val data = intent.getParcelableExtra<ItemBayarSpp>("data")

        if(data != null) {
            tv_nama_murid_konfirm.text = "Rahadika Fernandian" //bisa diganti dari sharedPreferences
            tv_value_bulankonfirm.text = data.bulan
            tv_value_jumlahkonfirm.text = "Rp.330.000" //harus ngambil dari database
            tv_value_statuskonfirm.text = data.status
            if(data.bukti != 0) {
                img_konfrim.setImageResource(data.bukti!!)
                btn_unggah_bayar.visibility = View.INVISIBLE
                btn_simpan_bayar.visibility = View.INVISIBLE
            }
        }

        img_back_konfirm_spp.setOnClickListener {
            onBackPressed()
        }

        btn_unggah_bayar.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }
    }

    //buat dapetin extension gambar. dipake nanti buat firebase
    private fun getFileExtension(imgLocation: Uri): String? {
        val contentResolver = contentResolver
        val mimeTypeMap = MimeTypeMap.getSingleton()
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imgLocation))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1 && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            img_location = data.data!!
            img_konfrim.setImageURI(img_location)
        }
    }
}