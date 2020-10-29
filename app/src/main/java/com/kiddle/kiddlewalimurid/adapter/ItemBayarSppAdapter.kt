package com.kiddle.kiddlewalimurid.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.model.ItemBayarSpp
import com.kiddle.kiddlewalimurid.ui.KonfirmasiPembayaranActivity

class ItemBayarSppAdapter(private var data: List<ItemBayarSpp>, private val listener: (ItemBayarSpp) -> Unit): RecyclerView.Adapter<ItemBayarSppAdapter.ViewHolder>() {

    lateinit var contextAdapter: Context
    private val listPembayaran = ArrayList<ItemBayarSpp>()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val tv_bulan_bayar:TextView = view.findViewById(R.id.tv_bulan_bayar)
        private val tv_status_bayar:TextView = view.findViewById(R.id.tv_status_bayar)

        fun bindItem(data: ItemBayarSpp, listener: (ItemBayarSpp) -> Unit, context: Context, position: Int) {
            tv_bulan_bayar.text = data.bulan
            val sharedPreferences = context.getSharedPreferences("KIDDLE", Context.MODE_PRIVATE)
            FirebaseFirestore.getInstance().collection("Pembayaran Murid/${data.bulan}/Bukti").whereEqualTo("id_murid", sharedPreferences.getString("id_murid", "")).addSnapshotListener { result, e ->
                if(e != null){
                    return@addSnapshotListener
                }
                if(result!!.isEmpty){
                    tv_status_bayar.text = "Belum Dibayar"
                } else {
                    for(document in result!!){
                        tv_status_bayar.text = document.getString("status")
                    }
                }
            }

            itemView.setOnClickListener {
                listener(data)
            }

            listener.invoke(data)
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
        holder.itemView.setOnClickListener {
            it.context.startActivity(Intent(it.context, KonfirmasiPembayaranActivity::class.java).putExtra("data", data[position]))
        }
    }

    fun addItemToList(list: ArrayList<ItemBayarSpp>) {
        listPembayaran.clear()
        listPembayaran.addAll(list)
    }
}