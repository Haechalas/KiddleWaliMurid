package com.kiddle.kiddlewalimurid.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.fragments.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val berandaFragment = Beranda()
        val jurnalFragment = Jurnal()
        val raporFragment = Rapor()
        val pembayaranFragment = Pembayaran()
        val profilFragment = Profil()

        makeCurrentFragment(berandaFragment)

    navigationBar.setOnNavigationItemSelectedListener {
        when(it.itemId){
        R.id.berandaNav -> makeCurrentFragment(berandaFragment)
        R.id.jurnalNav -> makeCurrentFragment(jurnalFragment)
        R.id.raporNav -> makeCurrentFragment(raporFragment)
        R.id.pembayaranNav -> makeCurrentFragment(pembayaranFragment)
        R.id.profilNav -> makeCurrentFragment(profilFragment)
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