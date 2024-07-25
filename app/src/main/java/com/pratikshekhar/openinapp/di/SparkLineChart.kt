package com.pratikshekhar.openinapp.di

import android.R.attr
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.pratikshekhar.openinapp.R
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Calendar


class SparkLineChart (private  val context:Context){


    fun styleChart(lineChart: LineChart) = lineChart.apply {
        axisRight.isEnabled = false

        axisLeft.apply {
            isEnabled = true
            axisMinimum = 0f
            axisMaximum = 100f
            typeface = ResourcesCompat.getFont(context, R.font.font_family_regular)
            textColor = ContextCompat.getColor(context, R.color.DarkTrunks)
        }

        xAxis.apply {
            axisMinimum = 0f
            axisMaximum = 23f
            isGranularityEnabled = true
            granularity = 4f
            setDrawGridLines(true)
            setDrawAxisLine(true)
            position = XAxis.XAxisPosition.BOTTOM

            valueFormatter = TimeValueFormatter()
          typeface = ResourcesCompat.getFont(context, R.font.font_family_regular)
           textColor = ContextCompat.getColor(context, R.color.DarkTrunks)
        }

        setTouchEnabled(true)
        isDragEnabled = true
        setScaleEnabled(false)
        setPinchZoom(false)

        description = null
        legend.isEnabled = false
    }

    fun styleLineDataSet(lineDataSet: LineDataSet) = lineDataSet.apply {
        color = ContextCompat.getColor(context, R.color.primary)
       valueTextColor = ContextCompat.getColor(context, R.color.DarkTrunks)
        setDrawValues(false)
        lineWidth = 3f
        isHighlightEnabled = true
        setDrawHighlightIndicators(false)
        setDrawCircles(false)
        mode = LineDataSet.Mode.LINEAR

        setDrawFilled(true)
       fillDrawable = ContextCompat.getDrawable(context, R.drawable.bg_spark_line)
    }

    private class TimeValueFormatter : ValueFormatter() {
        override fun getFormattedValue(value: Float): String {
            val calendar: Calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, attr.value)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)

            val timeFormatter: SimpleDateFormat = SimpleDateFormat("HH:mm")
            val formattedTime: String = timeFormatter.format(calendar.getTime())

            return formattedTime
        }
    }
}