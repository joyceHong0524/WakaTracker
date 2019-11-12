/**
 * @author Joyce Hong
 * @email soja0524@gmail.com
 * @created 2019-11-12
 * @desc
 */

package com.junga.wakatracker

import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class GraphAxisValueFormatter : IndexAxisValueFormatter{


    var mValue = arrayOf<String>()
    constructor(values : Array<String>){
        mValue = values;
    }
    override fun getFormattedValue(value: Float): String {
        return mValue[value.toInt()]
    }

}