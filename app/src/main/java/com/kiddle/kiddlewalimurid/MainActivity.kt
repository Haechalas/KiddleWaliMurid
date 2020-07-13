package com.kiddle.kiddlewalimurid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kiddle.kiddlewalimurid.fragments.*
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