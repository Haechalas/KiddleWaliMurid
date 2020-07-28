package com.kiddle.kiddlewalimurid.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.UI.DetailJurnalActivity
import com.kiddle.kiddlewalimurid.adapter.JurnalAdapter
import com.kiddle.kiddlewalimurid.model.Jurnal
import kotlinx.android.synthetic.main.fragment_jurnal.view.*
import java.util.*

class JurnalFragment : Fragment()  {

    //untuk menyimpan JurnalActivity
    private var jurnal = ArrayList<Jurnal>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_jurnal, container, false)

        //recyclerView jurnal
        view.rv_jurnal.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        //mengkosongkan isi arraylist
        jurnal.clear()

        val temp2 = Jurnal("Motorik","Bintang A","Belajar Menghitung","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vitae tellus feugiat, efficitur lacus nec, maximus felis. Maecenas ultrices tempor enim, et malesuada nisl lacinia eget.","20 Juli 2020",R.drawable.image_detail_materi,0)
        jurnal.add(temp2)

        //agar murid dapat di-click sekaligus mengisi adapter dengan data di arraylist tadi
        view.rv_jurnal.adapter = JurnalAdapter(jurnal) {
                val intent: Intent =Intent(activity, DetailJurnalActivity::class.java).putExtra("data",it)
                startActivity(intent)
            }

        return view
    }

}