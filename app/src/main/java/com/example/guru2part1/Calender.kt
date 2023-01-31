package com.example.guru2part1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.CalendarView
import android.widget.ImageView



class Calender : AppCompatActivity() {
    private lateinit var calenderAdd: ImageView
    private lateinit var calenderHome: ImageView
    private lateinit var calendarView: CalendarView
    private lateinit var buttonAdd :Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calender)
        calenderAdd=findViewById(R.id.calender_add)
        calenderHome=findViewById(R.id.calender_home)
        calendarView = findViewById(R.id.calendarView)
        buttonAdd=findViewById(R.id.addSched)


        buttonAdd.setOnClickListener {
            val intent= Intent(applicationContext,SubMediAdd::class.java)
            startActivity(intent)
        }

        calenderAdd.setOnClickListener {
            val intent= Intent(this,Add::class.java)
            startActivity(intent)
        }

        calenderHome.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }

}