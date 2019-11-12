package com.junga.wakatracker

import DayAxisValueFormatter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.model.GradientColor
import com.github.mikephil.charting.utils.ColorTemplate
import com.xxmassdeveloper.mpchartexample.custom.XYMarkerView
import kotlinx.android.synthetic.main.activity_chart_demo.*
import java.util.*


class PieChartActivity : AppCompatActivity() {

    lateinit var chart: BarChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart_demo)

        chart = chart1
        chart.run {
            setDrawBarShadow(false)
            setDrawValueAboveBar(true)
            setMaxVisibleValueCount(50)

        }
        val xAxisFormatter = DayAxisValueFormatter(chart)

        val xAxis = chart.getXAxis()
        with(xAxis) {
            position = XAxis.XAxisPosition.BOTTOM
            setDrawGridLines(false)

            granularity = 3f
            valueFormatter = xAxisFormatter
        }

        val leftAxis = chart.axisLeft

        with(leftAxis) {
            setLabelCount(8, false)
//            spaceTop // 위에 공백을 설정
            setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
            axisMinimum = 0f

        }

       val rightAxis = chart.axisRight

        with(rightAxis) {
            setLabelCount(8, false)
            setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
            axisMinimum = 0f
        }


        val nv = XYMarkerView(this, xAxisFormatter)
        nv.chartView = chart
        chart.marker = nv

        setData(10, 12f)


    }

    private fun setData(count: Int, range: Float) {

        val values = ArrayList<BarEntry>()


        values.add(BarEntry(1f,10f))
        values.add(BarEntry(2f,10f))
        values.add(BarEntry(10f,3f))
        values.add(BarEntry(4f,10f))
        values.add(BarEntry(5f,20f))
        values.add(BarEntry(6f,10f))
        values.add(BarEntry(7f,10f))

        val set1: BarDataSet

        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
            set1 = chart.data.getDataSetByIndex(0) as BarDataSet
            set1.values = values
            chart.getData().notifyDataChanged()
            chart.notifyDataSetChanged()

        } else {
            set1 = BarDataSet(values, "The year 2017")

            set1.setDrawIcons(false)

                        set1.setColors(ColorTemplate.VORDIPLOM_COLORS.toMutableList());

            /*int startColor = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
            int endColor = ContextCompat.getColor(this, android.R.color.holo_blue_bright);
            set1.setGradientColor(startColor, endColor);*/

            val startColor1 = ContextCompat.getColor(this, android.R.color.holo_orange_light)
            val startColor2 = ContextCompat.getColor(this, android.R.color.holo_blue_light)
            val startColor3 = ContextCompat.getColor(this, android.R.color.holo_orange_light)
            val startColor4 = ContextCompat.getColor(this, android.R.color.holo_green_light)
            val startColor5 = ContextCompat.getColor(this, android.R.color.holo_red_light)
            val endColor1 = ContextCompat.getColor(this, android.R.color.holo_blue_dark)
            val endColor2 = ContextCompat.getColor(this, android.R.color.holo_purple)
            val endColor3 = ContextCompat.getColor(this, android.R.color.holo_green_dark)
            val endColor4 = ContextCompat.getColor(this, android.R.color.holo_red_dark)
            val endColor5 = ContextCompat.getColor(this, android.R.color.holo_orange_dark)

            val gradientColors = ArrayList<GradientColor>()
            gradientColors.add(GradientColor(startColor1, endColor1))
            gradientColors.add(GradientColor(startColor2, endColor2))
            gradientColors.add(GradientColor(startColor3, endColor3))
            gradientColors.add(GradientColor(startColor4, endColor4))
            gradientColors.add(GradientColor(startColor5, endColor5))

//            set1.gradientColors = gradientColors

            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)

            val data = BarData(dataSets)
            data.setValueTextSize(10f)
            data.barWidth = 0.9f

            chart.setData(data)
            chart.invalidate()

        }
    }

}