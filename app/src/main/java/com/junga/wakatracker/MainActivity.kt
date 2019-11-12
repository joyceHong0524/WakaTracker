package com.junga.wakatracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import android.view.MenuItem
import androidx.annotation.NonNull

import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val TAG = MainActivity :: class.java.simpleName





        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
                when (item.getItemId()) {
                    R.id.navigation_menu1 -> {
                        Log.d(TAG,"menu1")
                    }
                    R.id.navigation_menu2 -> {
                        Log.d(TAG,"menu2")

                    }
                    R.id.navigation_menu3 -> {
                        Log.d(TAG,"menu3")

                    }

                }

                return true
            }
        })
    }
}
