package com.kiddle.kiddlewalimurid.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val berandaFragment = BerandaFragment()
        val jurnalFragment = JurnalFragment()
        val raporFragment = RaporFragment()
        val pembayaranFragment = PembayaranFragment()
        val profilFragment = ProfilFragment()

        makeCurrentFragment(berandaFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_beranda -> makeCurrentFragment(berandaFragment)
                R.id.menu_jurnal -> makeCurrentFragment(jurnalFragment)
                R.id.menu_rapor -> makeCurrentFragment(raporFragment)
                R.id.menu_pembayaran -> makeCurrentFragment(pembayaranFragment)
                R.id.menu_profil -> makeCurrentFragment(profilFragment)
            }
            true
        }
    }
    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_wrapper, fragment)
            commit()
        }
}