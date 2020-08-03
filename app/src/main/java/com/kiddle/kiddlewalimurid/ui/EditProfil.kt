package com.kiddle.kiddlewalimurid.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.kiddle.kiddlewalimurid.R
import kotlinx.android.synthetic.main.activity_edit_profil.*
import java.lang.StringBuilder

class EditProfil : AppCompatActivity() {

    lateinit var img_location: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profil)

        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        val sharedPreferences = getSharedPreferences("KIDDLE", Context.MODE_PRIVATE)
        var storage:StorageReference

        Glide.with(this).load(intent.getStringExtra("avatar")).centerCrop().into(img_edit_murid)
        edit_nama_murid.setText(intent.getStringExtra("nama"))
        edit_password_murid.setText(intent.getStringExtra("password"))
        edit_alamat_murid.setText(intent.getStringExtra("alamat"))
        edit_ttl_murid.setText(intent.getStringExtra("ttl"))
        edit_ayah_murid.setText(intent.getStringExtra("ayah"))
        edit_ibu_murid.setText(intent.getStringExtra("ibu"))
        edit_kontak_ayah.setText(intent.getStringExtra("kontak_ayah"))
        edit_kontak_ibu.setText(intent.getStringExtra("kontak_ibu"))

        img_back_edit_murid.setOnClickListener {
            onBackPressed()
        }

        btn_upload_murid.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }

        btn_simpan_murid.setOnClickListener {
            btn_simpan_murid.isEnabled = false
            btn_simpan_murid.text = "Loading"

            if(edit_nama_murid.text.toString().isEmpty() || edit_password_murid.text.toString().isEmpty()) {
                Toast.makeText(this, "Nama dan Kata Sandi tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                btn_simpan_murid.isEnabled = true
                btn_simpan_murid.text = "Simpan"
            } else {
                val builder = StringBuilder()
                builder.append(System.currentTimeMillis()).append(".").append(getFileExtension(img_location))

                storage = FirebaseStorage.getInstance().reference.child("Murid").child(sharedPreferences.getString("id_murid", "").toString()).child(builder.toString())
                storage.putFile(img_location).addOnSuccessListener{
                    storage.downloadUrl.addOnSuccessListener {
                        db.document("Murid/${sharedPreferences.getString("id_murid", "")}").update(
                            mapOf("avatar" to it.toString(), "nama" to edit_nama_murid.text.toString() ,
                                "password" to edit_password_murid.text.toString(), "alamat" to edit_alamat_murid.text.toString() ,
                                "ttl" to edit_ttl_murid.text.toString(), "ayah" to edit_ayah_murid.text.toString(), "ibu" to edit_ibu_murid.text.toString(),
                                "kontak_ayah" to edit_kontak_ayah.text.toString() , "kontak_ibu" to edit_kontak_ibu.text.toString() )
                        )
                    }.addOnCompleteListener {
                        onBackPressed()
                    }
                }
            }
        }
    }

    //buat dapetin extension gambar. dipake nanti buat firebase
    private fun getFileExtension(imgLocation:Uri): String? {
        val contentResolver = contentResolver
        val mimeTypeMap = MimeTypeMap.getSingleton()
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imgLocation))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1 && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            img_location = data.data!!
            img_edit_murid.setImageURI(img_location)
        }
    }
}