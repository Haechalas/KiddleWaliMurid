package com.kiddle.kiddlewalimurid.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.adapter.JurnalAdapter
import com.kiddle.kiddlewalimurid.model.jurnal
import kotlinx.android.synthetic.main.fragment_jurnal.view.*
import java.util.*

class JurnalFragment : Fragment()  {


    private var jurnal = ArrayList<jurnal>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_jurnal, container, false)

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