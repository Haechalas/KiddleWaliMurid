package com.kiddle.kiddlewalimurid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.kiddle.kiddlewalimurid.R
import kotlinx.android.synthetic.main.fragment_rapor.*
import kotlinx.android.synthetic.main.fragment_rapor.view.*
import net.cachapa.expandablelayout.ExpandableLayout

class RaporFragment : Fragment() , View.OnClickListener {

    var kognitif:Boolean = false
    var berbahasa:Boolean = false
    var keterampilan:Boolean = false
    var agama:Boolean = false
    var motorik:Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_rapor, container, false)

        //untuk dropdown semester

        //untuk dropdown semester
        val semester = listOf("Ganjil", "Genap")
        val adapter_semester = ArrayAdapter<String>(requireActivity().applicationContext, R.layout.item_dropdown_text, semester)
        (view.dropdown_rapor_semester.editText as? AutoCompleteTextView)?.setAdapter(adapter_semester)

        //untuk dropdown kelas
        val kelas = listOf("Bintang Kecil", "Bintang Besar", "Bulan Kecil", "Bulan Besar")
        val adapter_kelas = ArrayAdapter<String>(requireActivity().applicationContext, R.layout.item_dropdown_text, kelas)
        (view.dropdown_rapor_nilai.editText as? AutoCompleteTextView)?.setAdapter(adapter_kelas)

        //set on-click listener
        view.expand_button_0.setOnClickListener(this)
        view.expand_button_1.setOnClickListener(this)
        view.expand_button_2.setOnClickListener(this)
        view.expand_button_3.setOnClickListener(this)
        view.expand_button_4.setOnClickListener(this)

        return view
    }
    //ketika dropdown aktif di-click
    fun nonaktif(layout:ExpandableLayout, trigger:TextView) {
        layout.collapse()
        trigger.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown, 0)
    }
    //accordion sehingga hanya satu expandable layout yang aktif
    override fun onClick(v: View?) {
        if (v != null) {
            if(v.id == R.id.expand_button_0 && !kognitif){
                kognitif = true
                expandable_layout_0.expand()
                expandable_layout_1.collapse()
                expandable_layout_2.collapse()
                expandable_layout_3.collapse()
                expandable_layout_4.collapse()

                expand_button_0.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown_active, 0)
                expand_button_1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown, 0)
                expand_button_2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown, 0)
                expand_button_3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown, 0)
                expand_button_4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown, 0)
            } else if(v.id == R.id.expand_button_0 && kognitif){
                kognitif = false
                nonaktif(expandable_layout_0, expand_button_0)
            } else if(v.id == R.id.expand_button_1 && !berbahasa) {
                berbahasa = true
                expandable_layout_0.collapse()
                expandable_layout_1.expand()
                expandable_layout_2.collapse()
                expandable_layout_3.collapse()
                expandable_layout_4.collapse()

                expand_button_0.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown, 0)
                expand_button_1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown_active, 0)
                expand_button_2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown, 0)
                expand_button_3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown, 0)
                expand_button_4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown, 0)
            } else if(v.id == R.id.expand_button_1 && berbahasa) {
                berbahasa = false
                nonaktif(expandable_layout_1, expand_button_1)
            } else if(v.id == R.id.expand_button_2 && !keterampilan) {
                keterampilan = true
                expandable_layout_0.collapse()
                expandable_layout_1.collapse()
                expandable_layout_2.expand()
                expandable_layout_3.collapse()
                expandable_layout_4.collapse()

                expand_button_0.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown, 0)
                expand_button_1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown, 0)
                expand_button_2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown_active, 0)
                expand_button_3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown, 0)
                expand_button_4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown, 0)
            } else if(v.id == R.id.expand_button_2 && keterampilan) {
                keterampilan = false
                nonaktif(expandable_layout_2, expand_button_2)
            } else if(v.id == R.id.expand_button_3 && !agama) {
                agama = true
                expandable_layout_0.collapse()
                expandable_layout_1.collapse()
                expandable_layout_2.collapse()
                expandable_layout_3.expand()
                expandable_layout_4.collapse()

                expand_button_0.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown, 0)
                expand_button_1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown, 0)
                expand_button_2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown, 0)
                expand_button_3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown_active, 0)
                expand_button_4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown, 0)
            } else if(v.id == R.id.expand_button_3 && agama) {
                agama = false
                nonaktif(expandable_layout_3, expand_button_3)
            } else if(v.id == R.id.expand_button_4 && !motorik) {
                motorik = true
                expandable_layout_0.collapse()
                expandable_layout_1.collapse()
                expandable_layout_2.collapse()
                expandable_layout_3.collapse()
                expandable_layout_4.expand()

                expand_button_0.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown, 0)
                expand_button_1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown, 0)
                expand_button_2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown, 0)
                expand_button_3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown, 0)
                expand_button_4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown_active, 0)
            } else if(v.id == R.id.expand_button_4 && motorik) {
                motorik = false
                nonaktif(expandable_layout_4, expand_button_4)
            }
        }
    }
}


