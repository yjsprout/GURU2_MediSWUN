package com.example.guru2part1

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sub_medi_add.*
import java.util.*
import java.util.Calendar.getInstance

class SubMediAdd : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_medi_add)

        //DB 사용 위해서 추가함
        val myhelper =SqliteHelper(this,"medDB",null,1)

        var startDateString= ""
        startDateBtn.setOnClickListener {
            val cal = Calendar.getInstance()
            val dateSetListener = DatePickerDialog.OnDateSetListener{
                view, year, month, dayOfMonth ->
                    startDateString="$year-${month+1}-$dayOfMonth"
                startDateBtn.text=startDateString
            }
            DatePickerDialog(this,dateSetListener,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        var endDateString= ""
        endDateBtn.setOnClickListener {
            val cal = Calendar.getInstance()
            val dateSetListener = DatePickerDialog.OnDateSetListener{
                    view, year, month, dayOfMonth ->
                endDateString="$year-${month+1}-$dayOfMonth"
                endDateBtn.text=endDateString
            }
            DatePickerDialog(this,dateSetListener,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        val mediAddSubmit:Button=findViewById(R.id.submit)
        mediAddSubmit.setOnClickListener {
            //DB에 쓸 값을 data class에 넣어주기
            val med1 = editTextMedName1.text.toString()
            val med2 = editTextMedName2.text.toString()
            val med3 = editTextMedName3.text.toString()
            val cat = categorySpinner.selectedItem.toString()
            val date1 = startDateString
            val date2 = endDateString
            val memo = memoEditText.text.toString()
            var data = MediClass(date1,date2,med1,med2,med3,cat,memo)
            //값을 쓸 수 있는 DB 실행
            myhelper!!.insertMemo(data)
        }

        val goBack:Button=findViewById(R.id.goback)
        goBack.setOnClickListener {
            val intent = Intent(this, Calender::class.java)
            startActivity(intent)
        }

    }
}

data class MediClass(var startDate : String, var endDate : String, var medName1 : String, var medName2 : String, var medName3 : String, var category : String, var content : String)