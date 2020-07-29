package com.kiddle.kiddlewalimurid.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import com.kiddle.kiddlewalimurid.R
import kotlinx.android.synthetic.main.activity_edit_profil.*

class EditProfil : AppCompatActivity() {

    lateinit var img_location: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profil)

        img_back_edit_murid.setOnClickListener {
            onBackPressed()
        }

        btn_upload_murid.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
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