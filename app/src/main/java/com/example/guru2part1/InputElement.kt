package com.example.guru2part1

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText


class InputElement(context: Context) : Dialog(context) {

    private val inputElement = Dialog(context)

    fun show_inputDig(){

        inputElement.setContentView(R.layout.activity_input_element)//띄울 xml 파일 찾아주기

        //요소들 하나씩 연결해주기
        val inputelement = inputElement.findViewById<EditText>(R.id.inputelement)
        val cancelBT = inputElement.findViewById<Button>(R.id.addcancel)
        val saveBT = inputElement.findViewById<Button>(R.id.saveBT)

        cancelBT.setOnClickListener {
            inputElement.dismiss()
        }

        saveBT.setOnClickListener {
            onClickedListener.onClicked(inputelement.text.toString())//입력받은 문자열 String 타입으로 변환해서 전달
            inputElement.dismiss()//그리고 창 닫기
        }

        inputElement.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        inputElement.setCanceledOnTouchOutside(true)//다이얼로그 외부 터치하면, 다이얼로그 닫힘
        inputElement.setCancelable(true)

        inputElement.show()
    }

    interface ButtonClickListener{
        //알단 정의만, 얘 호출하는 액티비티에서 오버라이드 할 것
        fun onClicked(medi_Name : String)
    }

    private lateinit var onClickedListener: ButtonClickListener

    fun setOnClickedListener(listener: ButtonClickListener){
        onClickedListener = listener
    }

}