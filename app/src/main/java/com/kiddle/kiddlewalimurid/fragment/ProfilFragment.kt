package com.kiddle.kiddlewalimurid.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.ui.EditProfil
import com.kiddle.kiddlewalimurid.ui.PengaturanActivity
import kotlinx.android.synthetic.main.fragment_profil.*
import kotlinx.android.synthetic.main.fragment_profil.view.*

class ProfilFragment : Fragment() {

    lateinit  var password:String
    lateinit var avatar:String

    override fun onStart() {
        super.onStart()

        val sharedPreferences = activity?.getSharedPreferences("KIDDLE", Context.MODE_PRIVATE)
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()

        db.document("Murid/${sharedPreferences?.getString("id_murid", "")}").get().addOnSuccessListener {
            tv_murid_nama.text = it.getString("nama")
            tv_murid_nomor.text = it.getString("nomor")
            tv_murid_kelas.text = it.getString("kelas")
            tv_murid_ttl.text = it.getString("ttl")
            tv_murid_alamat.text = it.getString("alamat")
            tv_murid_ayah.text = it.getString("ayah")
            tv_murid_ibu.text = it.getString("ibu")
            tv_kontak_ayah.text = it.getString("kontak_ayah")
            tv_kontak_ibu.text = it.getString("kontak_ibu")

            avatar = it.getString("avatar").toString()
            password = it.getString("password").toString()

            Glide.with(this).load(it.getString("avatar")).centerCrop().into(img_avatar_detail_murid)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profil, container, false)

        view.menu_pengaturan.setOnClickListener {
            val popupMenu = PopupMenu(activity, it)
            popupMenu.setOnMenuItemClickListener {
                if(it.itemId == R.id.menu_pengaturan) {
                    startActivity(Intent(activity, PengaturanActivity::class.java))
                    return@setOnMenuItemClickListener true
                } else if(it.itemId == R.id.menu_edit_profil) {
                    var intent = Intent(activity, EditProfil::class.java)

                    intent.putExtra("avatar", avatar)
                    intent.putExtra("nama", tv_murid_nama.text.toString())
                    intent.putExtra("password", password)
                    intent.putExtra("alamat", tv_murid_alamat.text.toString())
                    intent.putExtra("ttl", tv_murid_ttl.text.toString())
                    intent.putExtra("ayah", tv_murid_ayah.text.toString())
                    intent.putExtra("ibu", tv_murid_ibu.text.toString())
                    intent.putExtra("kontak_ayah", tv_kontak_ayah.text.toString())
                    intent.putExtra("kontak_ibu", tv_kontak_ibu.text.toString())

                    startActivity(intent)
                    return@setOnMenuItemClickListener true
                }
                return@setOnMenuItemClickListener false
            }
            popupMenu.inflate(R.menu.menu_pengaturan)
            popupMenu.show()
        }

        //intent untuk manggil ortu
        view.btn_telepon_ayah.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + view.tv_kontak_ayah.text)
            startActivity(intent)
        }
        view.btn_telepon_ibu.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + view.tv_kontak_ibu.text)
            startActivity(intent)
        }

        return view

    }
}