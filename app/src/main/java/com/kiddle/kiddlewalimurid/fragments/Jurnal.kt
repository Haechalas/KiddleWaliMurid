package com.kiddle.kiddlewalimurid.fragments


import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.adapter.JurnalAdapter
import com.kiddle.kiddlewalimurid.model.jurnal
import kotlinx.android.synthetic.main.jurnal_fragment.*
import kotlinx.android.synthetic.main.jurnal_fragment.view.*
import java.util.*

class Jurnal : Fragment()  {

    //buat var
    var day = 0
    var month = 0
    var year = 0

    //buat di save di textview
    var saveDay = 0
    var saveMonth = 0
    var saveYear = 0

    //View
    private var jurnal = ArrayList<jurnal>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.jurnal_fragment, container, false)

        //DatePicker



        //List item dalam RV
        val jurnal = listOf(
            jurnal("Menghitung", "30 maret", "kognitif"),
            jurnal("Menghitung", "30 maret", "kognitif"),
            jurnal("Menghitung", "30 maret", "kognitif"),
            jurnal("Menghitung", "30 maret", "kognitif")
        )
        //RVjurnal
        view.RecyclerViewJurnal.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        //AdapterJurnal
        view.RecyclerViewJurnal.adapter = JurnalAdapter(jurnal)

        return view
    }

}