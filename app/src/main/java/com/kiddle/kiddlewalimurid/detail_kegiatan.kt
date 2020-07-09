package com.kiddle.kiddlewalimurid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class detail_kegiatan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_kegiatan)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Kegiatan"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}