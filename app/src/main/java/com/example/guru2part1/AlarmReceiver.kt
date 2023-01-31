package com.example.guru2part1

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat

const val TAG = "AlarmReceiver"
var NOTIFICATION_ID = 0 //이게 requestcode니까 변수로 둬야 알람을 여러개 등록할 수 있음
const val CHANNEL_ID = "notification channel"

class AlarmReceiver : BroadcastReceiver() {


    lateinit var notificationManager: NotificationManager

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "Received intent : $intent")

        //필수구문1
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //밑에서 정의할 함수 2가지
        createNotificationChannel()//채널만들고
        deliverNotification(context)//notification 발생시키기
        Log.d(TAG, " RequestCode $NOTIFICATION_ID")

    }

    private fun deliverNotification(context: Context) {
        val contentIntent = Intent(context, SetAlarm::class.java)//수정사항1
        val contentPendingIntent = PendingIntent.getActivity(
            context,
            NOTIFICATION_ID,
            contentIntent,
            PendingIntent.FLAG_MUTABLE)

        val builder =
            NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("!!잠깐!!")
                .setContentText("약 먹을 시간 알려드립니다.")
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setFullScreenIntent(contentPendingIntent,true)//수정사항3

        builder.priority = NotificationCompat.PRIORITY_HIGH//이미 위에 있는 내용 같은데

        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    private fun createNotificationChannel(){
        //채널생성하고 채널 불빛, 색상, 진동여부, 채널 정보를 설정
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            //high로 설정하면 알림음 울리고 헤드업알림으로 표시해줌
            val notificationChannel = NotificationChannel(
                CHANNEL_ID, "채널생성했다", NotificationManager.IMPORTANCE_HIGH)

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)//진동여부 true로 설정
            notificationChannel.description = "어디에 뜨는건지 보고 수정하자"

            //필수구문2
            notificationManager.createNotificationChannel(notificationChannel)
        }


    }

}