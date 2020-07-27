package com.kiddle.kiddlewalimurid.fragments

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.UI.CaraPembayaranActivity
import com.kiddle.kiddlewalimurid.adapter.ItemBayarSppAdapter
import com.kiddle.kiddlewalimurid.model.ItemBayarSpp
import kotlinx.android.synthetic.main.activity_detail_tugas.*
import kotlinx.android.synthetic.main.fragment_pembayaran.*
import kotlinx.android.synthetic.main.fragment_pembayaran.view.*
import java.io.File

class PembayaranFragment : Fragment() {

    //untuk menyimpan murid
    private var spp = ArrayList<ItemBayarSpp>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pembayaran, container, false)

        //recyclerView spp
        view.rv_pembayaran.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        //mengkosongkan isi arraylist
        spp.clear()

        val temp = ItemBayarSpp("19990305", "Juli 2020", "Lunas", R.drawable.image_detail_materi)
        spp.add(temp)

        view.rv_pembayaran.adapter =
            ItemBayarSppAdapter(spp) {

            }

        view.btn_pilih_bukti.setOnClickListener {
            startActivityForResult(Intent(Intent.ACTION_GET_CONTENT).setType("*/*"), 1)
        }

        view.btn_cara_bayar.setOnClickListener {
            startActivity(Intent(activity, CaraPembayaranActivity::class.java))
        }

        view.btn_simpan_bukti.setOnClickListener {
            Toast.makeText(activity, "Simpan", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 1 && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            var uri: Uri = data.data!!
            var uriString = uri.toString()
            var file: File = File(uriString)
            var path = file.absolutePath
            var displayName = ""

            if(uriString.startsWith("content://")) {
                var cursor: Cursor? = null
                try {
                    cursor = activity?.contentResolver?.query(uri, null, null, null, null)
                    if (cursor != null && cursor.moveToFirst()) {
                        displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                    }
                } finally {
                    cursor!!.close()
                }
            } else if(uriString.startsWith("file://")) {
                displayName = file.name
            }
            view?.tv_nama_bukti?.visibility = View.VISIBLE
            view?.tv_nama_bukti?.text = displayName
        }

        super.onActivityResult(requestCode, resultCode, data)

    }
}