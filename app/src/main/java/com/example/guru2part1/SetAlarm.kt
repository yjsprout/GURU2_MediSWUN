package com.example.guru2part1

import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlinx.android.synthetic.main.activity_set_alarm.*



class SetAlarm : AppCompatActivity() {

    //필요한 변수들
    var medilist = mutableListOf<String>("인슐린 억제제")//스피너에 넣을 데이터 리스트 생성
    var mediname =""//약 이름 저장용

    var start_cal = Calendar.getInstance()
    var end_cal = Calendar.getInstance()



    //val inputElement = InputElement(this)

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_alarm)

        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        val intent = Intent(this, AlarmReceiver::class.java)

        //수정완료
        val pendingIntent = PendingIntent.getBroadcast(
            this, NOTIFICATION_ID, intent,
            PendingIntent.FLAG_MUTABLE)

        set_alarmCancel.setOnClickListener {
            finish()//취소버튼
        }

        alarmSave.setOnClickListener {


            //2. 알람보내기
            var cal = Calendar.getInstance()

            //이 구문을 end_date가 지나기 전까지 반복해줌, 위 코드 활용해서 수정하자
            if( System.currentTimeMillis() < end_cal.timeInMillis ){

                alarmManager.setRepeating(AlarmManager.RTC,
                    start_cal.timeInMillis,AlarmManager.INTERVAL_DAY,pendingIntent)//알람이 너무 부정확함...

                Log.d(TAG, "알람 첫날   ${start_cal.timeInMillis}")
                Log.d(TAG, "알람 마지막 날  ${end_cal.timeInMillis}")
                Log.d(TAG, "부팅 후 경과한 시간  ${System.currentTimeMillis()}")
            }
            //지났으면 알람해제
            else{
                alarmManager.cancel(pendingIntent)
            }

            finish()

        }


        //여기서부터 요소 찾아주고 각 기능 설정해주는 부분
        //var mediList = findViewById<Spinner>(R.id.mediList)//얘가 객체

        //어댑터 생성
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, medilist)

        //어댑터 설정
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        //스피너에 어댑터 적용
        mediList.adapter = adapter


        mediList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

                //adapter.setNotifyOnChange(true)//바뀐거 알려줘야한다고 해서 일단 추가
                mediname  = medilist[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //나중에 기본값 지정해주기
            }

        }



        inputname.setOnClickListener {
            val dialog_input = InputElement(this)
            dialog_input.show_inputDig()
            dialog_input.setOnClickedListener(object : InputElement.ButtonClickListener {
                override fun onClicked(medi_Name: String) {
                    add_spinner(medi_Name)
                    //동적으로 요소추가
                }
            })
        }


        //버튼 너비 넓히기
        start_DatePick.setOnClickListener {
            var start_text = ""

            val calendar = Calendar.getInstance()
            val datesetListener = DatePickerDialog.OnDateSetListener { view, year, month, day ->
                start_text = "${year}/${month+1}/${day}"
                start_DatePick.text = start_text
                start_cal.set(Calendar.YEAR,year)
                start_cal.set(Calendar.MONTH,month)
                start_cal.set(Calendar.DAY_OF_MONTH,day)
            }

            DatePickerDialog(
                this, datesetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        end_DatePick.setOnClickListener {
            var end_text = ""

            val calendar = Calendar.getInstance()
            val datesetListener = DatePickerDialog.OnDateSetListener { view, year, month, day ->
                end_text = "${year}.${month+1}.${day}"
                end_DatePick.text = end_text
                end_cal.set(year,month+1,day)
            }

            DatePickerDialog(
                this, datesetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()

        }

        //버튼3가 시간선택 버튼
        button3.setOnClickListener {
            var time_text = ""

            val cal = Calendar.getInstance()
            val timeSetListener =
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    time_text = "${hourOfDay}:${minute}"
                    button3.text = time_text

                    start_cal.set(Calendar.HOUR_OF_DAY,hourOfDay)
                    start_cal.set(Calendar.MINUTE,minute)

                    end_cal.set(Calendar.HOUR_OF_DAY,hourOfDay)
                    end_cal.set(Calendar.MINUTE,minute)
                }

            TimePickerDialog(
                this,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true
            ).show()

        }


    }


    fun add_spinner(medi_Name : String){
        medilist.add(medi_Name)//리스트에 추가

        //어댑터 생성
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, medilist)
        //어댑터 설정
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //어댑터 적용
        mediList.adapter = adapter
    }








}
