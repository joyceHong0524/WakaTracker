package com.junga.wakatracker

import DayAxisValueFormatter
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.model.GradientColor
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.junga.wakatracker.base.BaseComponents
import com.junga.wakatracker.db.MyDatabase
import com.junga.wakatracker.db.entity.TimeEntity
import com.junga.wakatracker.retrofit.Data
import com.junga.wakatracker.retrofit.IWakaServer
import com.junga.wakatracker.retrofit.RawResponseData
import com.junga.wakatracker.retrofit.RetrofitClient
import com.xxmassdeveloper.mpchartexample.custom.XYMarkerView
import kotlinx.android.synthetic.main.activity_chart_demo.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onCapturedPointer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : BaseComponents() {

    val TAG = MainActivity::class.java.simpleName

    lateinit var chart: BarChart
    lateinit var retrofit: Retrofit
    lateinit var myAPI: IWakaServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val TAG = MainActivity::class.java.simpleName
        Log.d(TAG, "on create!")
        setContentView(R.layout.activity_main)

        //retrofit setting

        retrofit = RetrofitClient.getInstnace()
        myAPI = retrofit.create(IWakaServer::class.java)




        //Test If room works.

        var todayDate = Calendar.getInstance().getTime();
        var formatter = SimpleDateFormat("yyyy-MM-dd");
        var todayString = formatter.format(todayDate);

        checkDB()



        Runnable {
            myAPI.getCodingTime(todayString, "8ae57a7c-9d9c-4e86-9997-c43e464bd35e")
                .enqueue(object : Callback<RawResponseData> {
                    override fun onFailure(call: Call<RawResponseData>, t: Throwable) {
                        Log.d(TAG, t.message)
                    }

                    override fun onResponse(call: Call<RawResponseData>, response: Response<RawResponseData>) {

                        Log.d(TAG, "is Null? ${response.body()}")


                        val responseData = response.body()

                        val a = getTimeSum(responseData!!.data!!);

                        val hour: Int = (a / 3600).toInt()
                        val min = ((a / 3600 - hour) * 60).toInt()

                        hour_tv.text = hour.toString()
                        mins.text = min.toString()


                        Log.d(TAG, "sum Of Time : ${a}")
                        Log.d(TAG, "response : ${response.body()!!.start}")
                        Log.d(TAG, "response : ${response.body()!!.data!!.size}")
                        Log.d(TAG, "response : ${response.body()!!.end}")
                        Log.d(TAG, "response : ${response.body()!!.timezone}")



                        if (response.code() != 200) {
                            //TODO: Can't change ui in worker thread!! need to be changed in RXJAVA!!
//                   Snackbar.make(root_layout,"Failed to fetch new data!",Snackbar.LENGTH_SHORT).show()

                        } else {

                        }
                        var context = applicationContext

                        var r = Runnable {
                            MyDatabase.getInstance(context)!!.getTimeDao()
                                .insertSingleTime(TimeEntity(null, Date(1573984225), 100000))
                            MyDatabase.getInstance(context)!!.getTimeDao()
                                .insertSingleTime(TimeEntity(null, Date(1573984222), 100000))
                            MyDatabase.getInstance(context)!!.getTimeDao()
                                .insertSingleTime(TimeEntity(null, Date(1573222225), 100000))
//                   MyDatabase.getInstance(context)!!.getTimeDao().insertSingleTime(TimeEntity(null, 1573984225, 100000))
//                   MyDatabase.getInstance(context)!!.getTimeDao().insertSingleTime(TimeEntity(null, 1573984225, 100000))
//                   MyDatabase.getInstance(context)!!.getTimeDao().insertSingleTime(TimeEntity(null, 1573984225, 100000))


                            val list = MyDatabase.getInstance(context)!!.getTimeDao().getTimes()

                            for (item in list) {
                                Log.d(TAG, item.toString())

//                       val time = Converters.fromTimestamp(item.date)
//                       Log.d(TAG,time.toString())
                            }
                        }


                        val thread = Thread(r)
                        thread.start()
                        //If something doesn't work don't forget to log all of these.
                        Log.d(TAG, "response : ${response.errorBody().toString()}")
                        Log.d(TAG, "response : ${response.message()}") //아무것도 안날라온다.
                        Log.d(TAG, "response : ${response.code()}") //402 : Need payment.
                        Log.d(TAG, "response : ${response.raw().request().url().url()}")

                    }
                })
        }.run()


        bottomNavigationView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

        bottomNavigationView.setOnClickListener ({

        })
        val list = ArrayList<Int>(2)

        list.stream()
            .forEach{ i -> println(i) }

        bottomNavigationView.onCapturedPointer{ view, event ->  }


        setChart()

        bottomNavigationView.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
                when (item.getItemId()) {
                    R.id.navigation_menu1 -> {
                        Log.d(TAG, "menu1")
                    }
                    R.id.navigation_menu2 -> {
                        Log.d(TAG, "menu2")

                    }
                    R.id.navigation_menu3 -> {
                        Log.d(TAG, "menu3")

                    }

                }

                return true
            }
        })
    }


    private fun setChart() {
        chart = chart1
        chart.setDrawBarShadow(false)
        chart.setDrawValueAboveBar(true)

        chart.description.isEnabled = false

        chart.animateY(1000)
        chart.run {
            setDrawBarShadow(false)
            setDrawValueAboveBar(true)
            setMaxVisibleValueCount(50)

        }
        val mMonths = arrayOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")

        val xAxisFormatter = DayAxisValueFormatter(chart)
        val xAxF = GraphAxisValueFormatter(mMonths)

        val xAxis = chart.getXAxis()
        with(xAxis) {
            position = com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM
            setDrawGridLines(false)
            granularity = 1f
            valueFormatter = xAxF
            setDrawGridLines(false)

        }

        val leftAxis = chart.axisLeft

        with(leftAxis) {
            //          spaceTop= 20f 위에 공백을 설정 전체 그래프 사이즈의 percent
            setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
            axisMaximum = 10f //TODO: 최대 시간을 조정할 수 있게끔 만들
            granularity = 1f
            spaceTop = 15f
            axisMinimum = 0f
            setDrawTopYLabelEntry(false)

        }

        //If it is not enabled, it shows table description bottom left.
        chart.description = null
        chart.legend.formSize = 0f
        val rightAxis = chart.axisRight

        // right YAxis not activated

        with(rightAxis) {
            setDrawLabels(false)
            setDrawAxisLine(false)
            setDrawGridLines(false)
        }

        val nv = XYMarkerView(this, xAxisFormatter)
        nv.chartView = chart
        chart.marker = nv

        setData(7, 12f)

    }

    private fun setData(count: Int, range: Float) {

        val values = ArrayList<BarEntry>()


        values.add(BarEntry(0f, 6f))
        values.add(BarEntry(1f, 5f))
        values.add(BarEntry(2f, 3f))
        values.add(BarEntry(3f, 4f))
        values.add(BarEntry(4f, 5f))
        values.add(BarEntry(5f, 6f))
        values.add(BarEntry(6f, 4f))

        val set1: BarDataSet

        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
            set1 = chart.data.getDataSetByIndex(0) as BarDataSet
            set1.values = values
            chart.getData().notifyDataChanged()
            chart.notifyDataSetChanged()

        } else {
            set1 = BarDataSet(values, "")

            set1.setDrawIcons(false)

//            set1.setColors(ColorTemplate.VORDIPLOM_COLORS.toMutableList());
            /*int startColor = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
            int endColor = ContextCompat.getColor(this, android.R.color.holo_blue_bright);
            set1.setGradientColor(startColor, endColor);*/

            val startColor1 = ContextCompat.getColor(this, R.color.orange_bar)
            val startColor2 = ContextCompat.getColor(this, android.R.color.holo_blue_light)
            val startColor3 = ContextCompat.getColor(this, android.R.color.holo_orange_light)
            val startColor4 = ContextCompat.getColor(this, android.R.color.holo_green_light)
            val startColor5 = ContextCompat.getColor(this, android.R.color.holo_red_light)
            val endColor1 = ContextCompat.getColor(this, R.color.orange)
            val endColor2 = ContextCompat.getColor(this, android.R.color.holo_purple)
            val endColor3 = ContextCompat.getColor(this, android.R.color.holo_green_dark)
            val endColor4 = ContextCompat.getColor(this, android.R.color.holo_red_dark)
            val endColor5 = ContextCompat.getColor(this, android.R.color.holo_orange_dark)

            val gradientColors = ArrayList<GradientColor>()
            gradientColors.add(GradientColor(startColor1, endColor1))
//            gradientColors.add(GradientColor(startColor2, endColor2))
//            gradientColors.add(GradientColor(startColor3, endColor3))
//            gradientColors.add(GradientColor(startColor4, endColor4))
//            gradientColors.add(GradientColor(startColor5, endColor5))


            set1.gradientColors = gradientColors

            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)

            val data = BarData(dataSets)
            data.setValueTextSize(10f)
            data.barWidth = 0.9f

            chart.setData(data)
            chart.invalidate()

        }
    }

    private fun getTimeSum(data: Array<Data>): Float {

        var sumOfTime: Float = 0f;

        for (item in data) {
            Log.d(TAG, "duration of ${item.project} : ${item.duration}")
            sumOfTime += item.duration;

        }

        return sumOfTime; // retrun long type (timestamp) so that it can be converted to DATETIME later.
    }


    private fun checkDB() {


//             MyDatabase.getInstance(this)!!.getTimeDao().insertSingleTime(TimeEntity(null,Date(1573984225),100000))
//             MyDatabase.getInstance(this)!!.getTimeDao().insertSingleTime(TimeEntity(null,Date(1573984225),100000))
//             MyDatabase.getInstance(this)!!.getTimeDao().insertSingleTime(TimeEntity(null,Date(1573984225),100000))
//             MyDatabase.getInstance(this)!!.getTimeDao().insertSingleTime(TimeEntity(null,Date(1573984225),100000))
//             MyDatabase.getInstance(this)!!.getTimeDao().insertSingleTime(TimeEntity(null,Date(1573984225),100000))
//             MyDatabase.getInstance(this)!!.getTimeDao().insertSingleTime(TimeEntity(null,Date(1573984225),100000))
//             MyDatabase.getInstance(this)!!.getTimeDao().insertSingleTime(TimeEntity(null,Date(1573984225),100000))
//             MyDatabase.getInstance(this)!!.getTimeDao().insertSingleTime(TimeEntity(null,Date(1573984225),100000))


    }
}
