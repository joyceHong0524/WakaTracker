/**
 * @author Joyce Hong
 * @email soja0524@gmail.com
 * @created 2019-11-12
 * @desc
 */

package com.junga.wakatracker.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.junga.wakatracker.R

/*
* Base class of all Activities of the WakaTracker.
*
* */

abstract class BaseComponents : AppCompatActivity() , ActivityCompat.OnRequestPermissionsResultCallback{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("base","On create")
    }
    override fun onBackPressed() {
        super.onBackPressed()
        Log.d("base","back button pressed!")
    }
}