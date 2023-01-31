package com.example.guru2part1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    lateinit var home_add: ImageView
    lateinit var home_calender:ImageView
    lateinit var edtNameResult:TextView
    lateinit var edtAny:EditText
    lateinit var sep_add:ImageView
    lateinit var name_add:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        home_add=findViewById(R.id.home_add)
        home_calender=findViewById(R.id.home_calender)
        edtNameResult=findViewById(R.id.edtNameResult)
        sep_add=findViewById(R.id.sep_add)
        name_add=findViewById(R.id.name_add)
        edtAny=findViewById(R.id.edtAny)

        loadData()
        loadData2()

        sep_add.setOnClickListener{
            saveData(edtAny.text.toString())
            intent.putExtra("sep",edtAny.text.toString())
            startActivity(intent)
        }

        name_add.setOnClickListener{
            saveData2(edtNameResult.text.toString())
            intent.putExtra("name",edtNameResult.text.toString())
            startActivity(intent)
        }




        home_add.setOnClickListener {
            var intent= Intent(this,Add::class.java)
            startActivity(intent)
        }

        home_calender.setOnClickListener {
            var intent= Intent(this,Calender::class.java)
            startActivity(intent)
        }

    }

    private fun saveData(sep:String){
        var pref=this.getPreferences(0)
        var editor=pref.edit()
        editor.putString("sep",edtAny.text.toString()).apply()
    }

    private fun loadData(){
        var pref=this.getPreferences(0)
        var sep=pref.getString("sep","")

        if(sep!=""){
            edtAny.setText(sep.toString())
        }
    }
    private fun saveData2(sep:String){
        var pref=this.getPreferences(0)
        var editor=pref.edit()
        editor.putString("name",edtNameResult.text.toString()).apply()
    }

    private fun loadData2(){
        var pref=this.getPreferences(0)
        var name=pref.getString("name","")

        if(name!=""){
            edtNameResult.setText(name.toString())
        }
    }


}