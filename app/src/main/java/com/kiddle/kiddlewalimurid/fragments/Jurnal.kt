package com.kiddle.kiddlewalimurid.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.adapter.JurnalAdapter
import com.kiddle.kiddlewalimurid.model.jurnal
import kotlinx.android.synthetic.main.jurnal_fragment.view.*
import java.util.ArrayList

class Jurnal : Fragment() {

    private var jurnal = ArrayList<jurnal>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.jurnal_fragment, container, false)

        val jurnal = listOf(
            jurnal("Menghitung", "30 maret", "kognitif"),
            jurnal("Menghitung", "30 maret", "kognitif"),
            jurnal("Menghitung", "30 maret", "kognitif")
        )
        //RVjurnal gak muncul :(
        view.RecyclerViewJurnal.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        //AdapterJurnal
        view.RecyclerViewJurnal.adapter = JurnalAdapter(jurnal)

        return view

    }
}