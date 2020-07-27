package com.kiddle.kiddlewalimurid.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.kiddle.kiddlewalimurid.R
import kotlinx.android.synthetic.main.activity_presensi.*

class PresensiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presensi)

        //untuk dropdown semester
        val bulan = listOf("Juli 2020", "Agustus 2020")
        val adapter_bulan = ArrayAdapter(this,R.layout.dropdown_text, bulan)
        (dropdown_presensi_semester.editText as? AutoCompleteTextView)?.setAdapter(adapter_bulan)

        img_back_presensi.setOnClickListener {
            onBackPressed()
        }
    }
}