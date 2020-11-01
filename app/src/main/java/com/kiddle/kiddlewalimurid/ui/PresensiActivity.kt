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
    val bulan = mutableListOf<String>()
    val rekap: ArrayList<String> = arrayListOf()
    var hadir = 0
    var izin = 0
    var sakit = 0
    var alpha = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presensi)

        val db:FirebaseFirestore = FirebaseFirestore.getInstance()
        val sharedPreferences = getSharedPreferences("KIDDLE", Context.MODE_PRIVATE)

        text_hadir.text = hadir.toString()
        text_alpha.text = alpha.toString()
        text_izin.text = izin.toString()
        text_sakit.text = sakit.toString()


        db.document("Bulan Rekap Presensi/${sharedPreferences.getString("id_murid", "")}").collection("Bulan").get().addOnSuccessListener {
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
            rekap.clear()
            var item = parent.getItemAtPosition(position).toString()
            db.collection("Rekap Presensi/${sharedPreferences.getString("id_murid", "")}/Bulan/$item/Tanggal").addSnapshotListener { result, e ->
                if (e != null) {
                    return@addSnapshotListener
                }
                if(result!!.isEmpty){
                    Toast.makeText(this, " Tidak Tersedia", Toast.LENGTH_SHORT).show()
                }else {
                    for (document in result!!) {
                        rekap.add(document.getString("kehadiran")!!)
                    }
                }

                for(i in 0 until rekap.size){
                    if(rekap[i]=="hadir"){
                        hadir += 1
                    }else if(rekap[i]=="izin"){
                        izin += 1
                    }else if(rekap[i]=="sakit"){
                        sakit += 1
                    }else if(rekap[i]=="alpha") {
                        alpha += 1
                    }
                    Log.d("hitung", hadir.toString())
                }

                text_hadir.text = hadir.toString()
                text_alpha.text = alpha.toString()
                text_izin.text = izin.toString()
                text_sakit.text = sakit.toString()

                hadir=0
                alpha=0
                izin=0
                sakit=0
            }
        }

        img_back_presensi.setOnClickListener {
            onBackPressed()
        }
    }
}

