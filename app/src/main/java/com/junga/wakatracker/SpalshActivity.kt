package com.junga.wakatracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.junga.wakatracker.base.BaseComponents
import org.jetbrains.anko.startActivity

class SpalshActivity : BaseComponents() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh)

        Handler().postDelayed({
            startActivity<MainActivity>()
        },2000)
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            startActivity<MainActivity>()
        },2000)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
