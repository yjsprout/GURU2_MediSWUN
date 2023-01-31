package com.example.guru2part1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

import com.google.android.material.floatingactionbutton.FloatingActionButton


class Add : AppCompatActivity() {
    lateinit var add_home: ImageView
    lateinit var add_calender: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        add_home = findViewById(R.id.add_home)
        add_calender = findViewById(R.id.add_calender)

        val alarmplus = findViewById<FloatingActionButton>(R.id.alarmplus)//타입을 써줘야한다...!!

        alarmplus.setOnClickListener{
            val intent1 = Intent(this, SetAlarm::class.java)//넘어갈 파일
            startActivity(intent1)
        }


        add_home.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        add_calender.setOnClickListener {
            var intent = Intent(this, Calender::class.java)
            startActivity(intent)
        }
    }
}
