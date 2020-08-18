package com.kiddle.kiddlewalimurid.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.kiddle.kiddlewalimurid.R
import kotlinx.android.synthetic.main.activity_presensi.*
import java.util.Collections.list

class PresensiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presensi)

        val db:FirebaseFirestore = FirebaseFirestore.getInstance()
        val sharedPreferences = getSharedPreferences("KIDDLE", Context.MODE_PRIVATE)

        val bulan = mutableListOf<String>()

        db.document("Presensi/${sharedPreferences.getString("kelas", "")}").collection("${sharedPreferences.getString("id_murid", "")}").get().addOnSuccessListener {
            for(snapshot in it.documents) {
                bulan.add(snapshot.id)
            }
        }
        val adapter_bulan = ArrayAdapter(this,R.layout.dropdown_text, bulan)
        (dropdown_presensi_semester.editText as? AutoCompleteTextView)?.setAdapter(adapter_bulan)

       auto_semester.setOnClickListener {
            Toast.makeText(this, "Mengambil data dari database, mohon tunggu!", Toast.LENGTH_SHORT).show()
        }

        auto_semester.setOnItemClickListener { parent, view, position, id ->
            var item = parent.getItemAtPosition(position).toString()
            db.document("Presensi/${sharedPreferences.getString("kelas", "")}/${sharedPreferences.getString("id_murid", "")}/$item").get().addOnSuccessListener {
                text_hadir.text = it.get("hadir").toString()
                text_alpha.text = it.get("alpha").toString()
                text_izin.text = it.get("izin").toString()
                text_sakit.text = it.get("sakit").toString()
            }
        }

        img_back_presensi.setOnClickListener {
            onBackPressed()
        }
    }
}

