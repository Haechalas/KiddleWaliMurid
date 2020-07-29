package com.kiddle.kiddlewalimurid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kiddle.kiddlewalimurid.R
import kotlinx.android.synthetic.main.activity_cara_pembayaran.*

class CaraPembayaranActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cara_pembayaran)

        img_back_cara_bayar.setOnClickListener {
            onBackPressed()
        }
    }
}