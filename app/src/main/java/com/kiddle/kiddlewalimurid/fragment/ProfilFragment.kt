package com.kiddle.kiddlewalimurid.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.UI.EditProfil
import com.kiddle.kiddlewalimurid.UI.PengaturanActivity
import kotlinx.android.synthetic.main.fragment_profil.view.*

class ProfilFragment : Fragment() {

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
                    startActivity(Intent(activity, EditProfil::class.java))
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