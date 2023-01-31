package com.example.guru2part1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
//https://inistory.tistory.com/118 공부
//https://itmoon.tistory.com/45 코드 참고
// SQLiteOpenHelper 상속받아 SQLite 를 사용하도록 하겠습니다.
class SqliteHelper(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    //onCreate(), onUpgrade() 두가지 메소드를 오버라이드 받아 줍시다.

    //데이터베이스가 만들어 지지않은 상태에서만 작동합니다. 이미 만들어져 있는 상태라면 실행되지 않습니다.
    override fun onCreate(db: SQLiteDatabase?) {
        //테이블을 생성할 쿼리를 작성하여 줍시다.
        val create = "create table mediTBL (startDate text, endDate text, medName1 text, medName2 text, medName3 text, category text, content text)"
        //startDate string, endDate string, medName1 string, medName2 string, medName3 string, category string, content string
        //실행시켜 줍니다.
        db?.execSQL(create)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    //insert 메소드
    fun insertMemo(medicine: MediClass){
        val values = ContentValues()
        //넘겨줄 컬럼의 매개변수 지정
        values.put("startDate",medicine.startDate)
        values.put("endDate",medicine.endDate)
        values.put("medName1",medicine.medName1)
        values.put("medName2",medicine.medName2)
        values.put("medName3",medicine.medName3)
        values.put("category",medicine.category)
        values.put("content",medicine.content)
        //쓰기나 수정이 가능한 데이터베이스 변수
        val wd = writableDatabase
        wd.insert("mediTBL",null,values)
        //사용이 끝나면 반드시 close()를 사용하여 메모리누수 가 되지않도록 합시다.
        wd.close()
    }

    //select 메소드
    /*fun selectMemo():MutableList<MediClass>{
        val list = mutableListOf<MediClass>()
        //전체조회
        val selectAll = "select * from medicine"
        //읽기전용 데이터베이스 변수
        val rd = readableDatabase
        //데이터를 받아 줍니다.
        val cursor = rd.rawQuery(selectAll,null)

        //반복문을 사용하여 list 에 데이터를 넘겨 줍시다.
        while(cursor.moveToNext()){
            val id = cursor.getLong(cursor.getColumnIndexOrThrow("id"))
            val mdname = cursor.getString(cursor.getColumnIndexOrThrow("mdname"))
            val content = cursor.getString(cursor.getColumnIndexOrThrow("content"))
            val datetime = cursor.getString(cursor.getColumnIndexOrThrow("datetime"))

            list.add(MediClass(id, mdname,datetime,content))
        }
        cursor.close()
        rd.close()

        return list
    }*/

    //update 메소드 - 수정하는 거 구현하려면 어떤 식으로?
    /*fun updateMemo(medicine: MediClass){
        val values = ContentValues()

        values.put("mdname",medicine.mdname)
        values.put("datetime",medicine.datetime)
        values.put("content",medicine.content)

        val wd = writableDatabase
        wd.update("medicine",values,"id=${medicine.id}",null)
        wd.close()

    }*/

    /*//delete 메소드
    fun deleteMemo(medicine: MediClass){
        val delete = "delete from mediTBL where id = ${medicine.id}"
        val db = writableDatabase
        db.execSQL(delete)
        db.close()

    }*/

}

//val startDate = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(it.first)