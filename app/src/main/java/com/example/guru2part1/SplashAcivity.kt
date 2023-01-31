package com.example.guru2part1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashAcivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}