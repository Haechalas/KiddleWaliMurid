package com.kiddle.kiddlewalimurid.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.model.ItemBayarSpp
import kotlinx.android.synthetic.main.activity_konfirmasi_pembayaran.*
import java.lang.StringBuilder

class KonfirmasiPembayaranActivity : AppCompatActivity() {

    lateinit var img_location: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konfirmasi_pembayaran)

        val data = intent.getParcelableExtra<ItemBayarSpp>("data")
        var storage: StorageReference
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        val sharedPreferences = getSharedPreferences("KIDDLE", Context.MODE_PRIVATE)
        var flag:Boolean = false

        if(data != null) {
            Glide.with(this).load(data.avatar).into(civ_profil_konfirmspp)
            tv_nama_murid_konfirm.text = data.nama
            tv_value_bulankonfirm.text = "${data.tanggal} ${data.bulan}"
            tv_value_jumlahkonfirm.text = "Rp. ${data.harga}"
            tv_value_statuskonfirm.text = data.status
            Glide.with(this).load(data.bukti).into(img_konfrim)

            if(!data.bukti.isNullOrEmpty()) {
                btn_unggah_bayar.visibility = View.INVISIBLE
                btn_simpan_bayar.visibility = View.INVISIBLE
            }
        } else {
            Glide.with(this).load(sharedPreferences.getString("avatar", "")).into(civ_profil_konfirmspp)
            tv_nama_murid_konfirm.text = sharedPreferences.getString("nama", "")
            tv_value_bulankonfirm.text = intent.getStringExtra("bulan")
            tv_value_jumlahkonfirm.text = "Rp. " + intent.getStringExtra("jumlah")
            img_konfrim.visibility = View.INVISIBLE

            db.document("Pembayaran Murid/${sharedPreferences.getString("kelas", "")}/${sharedPreferences.getString("id_murid", "")}/${tv_value_bulankonfirm.text.toString().substring(3)}").get().addOnSuccessListener {
                if(it.exists()) {
                    tv_value_statuskonfirm.text = it.getString("status")
                    Glide.with(this).load(it.getString("bukti")).into(img_konfrim)
                } else {
                    tv_value_statuskonfirm.text = "Belum Dibayar"
                }
            }
        }

        img_back_konfirm_spp.setOnClickListener {
            onBackPressed()
        }

        btn_unggah_bayar.setOnClickListener {
            flag = true
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }

        btn_simpan_bayar.setOnClickListener {
           if(flag && img_location != null) {
               btn_simpan_bayar.isEnabled = false
               btn_simpan_bayar.text = "Loading"
               btn_unggah_bayar.isEnabled = false

               val builder = StringBuilder()
               builder.append(sharedPreferences.getString("id_murid", "")).append("_").append(tv_value_bulankonfirm.text.toString().substring(3)).append(".").append(getFileExtension(img_location))

               storage = FirebaseStorage.getInstance().reference.child("Pembayaran Murid").child("${sharedPreferences.getString("kelas", "")}").child("${sharedPreferences.getString("id_murid", "")}").child(builder.toString())
               storage.putFile(img_location).addOnSuccessListener {
                    storage.downloadUrl.addOnSuccessListener {
                        db.document("Pembayaran Murid/${sharedPreferences.getString("kelas", "")}/${sharedPreferences.getString("id_murid", "")}/${tv_value_bulankonfirm.text.toString().substring(3)}").set(
                            mapOf(
                                "avatar" to sharedPreferences.getString("avatar", ""),
                                "bukti" to it.toString(),
                                "bulan" to tv_value_bulankonfirm.text.toString().substring(3),
                                "harga" to tv_value_jumlahkonfirm.text.toString().substring(4),
                                "nama" to sharedPreferences.getString("nama", ""),
                                "status" to "Belum Dikonfirmasi",
                                "tanggal" to tv_value_bulankonfirm.text.toString().substring(0, 2)
                            )
                        ).addOnCompleteListener {
                            startActivity(Intent(this, MainActivity::class.java))
                        }
                    }
               }
           } else {
               Toast.makeText(this, "Harap pilih bukti yang ingin di-upload!", Toast.LENGTH_SHORT).show()
           }
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

        img_konfrim.visibility = View.VISIBLE
    }
}