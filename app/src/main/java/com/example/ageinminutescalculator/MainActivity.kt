package com.example.ageinminutescalculator

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvInMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnSelectDate)
        tvSelectedDate = findViewById(R.id.tvSelectedData)
        tvInMinutes = findViewById(R.id.tvInMinutes)

        btnDatePicker.setOnClickListener {
            datePicker()
        }
    }

    private fun datePicker() {

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view, selectedYear, selectedMonth, selectedDayOfMonth ->

                tvSelectedDate?.text = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                Toast.makeText(this, "Date Set Successfuly", Toast.LENGTH_LONG).show()

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse("$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear")
                theDate?.let {
                    val selectedDateInMinutes = theDate.time/60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time/60000

                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                        tvInMinutes?.text = differenceInMinutes.toString()
                    }
                }
            },
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000*2
        dpd.show()

    }
}