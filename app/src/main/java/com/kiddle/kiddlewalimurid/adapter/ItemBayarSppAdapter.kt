package com.kiddle.kiddlewalimurid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.model.ItemBayarSpp

class ItemBayarSppAdapter(private var data: List<ItemBayarSpp>, private val listener: (ItemBayarSpp) -> Unit): RecyclerView.Adapter<ItemBayarSppAdapter.ViewHolder>() {

    lateinit var contextAdapter: Context

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val tv_bulan_bayar:TextView = view.findViewById(R.id.tv_bulan_bayar)
        private val tv_status_bayar:TextView = view.findViewById(R.id.tv_status_bayar)

        fun bindItem(data: ItemBayarSpp, listener: (ItemBayarSpp) -> Unit, context: Context, position: Int) {
            tv_bulan_bayar.text = data.bulan
            tv_status_bayar.text = data.status

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView: View = layoutInflater.inflate(R.layout.holder_pembayaran, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter, position)
    }
}