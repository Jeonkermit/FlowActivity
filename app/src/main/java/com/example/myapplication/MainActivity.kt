package com.example.myapplication

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.nio.channels.Channel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val number = findViewById<TextView>(R.id.number)
        val bundle = Bundle()

        val countButton = findViewById<Button>(R.id.myButton2)
        var num = 0
        countButton.setOnClickListener {
            num += 1
            number.text=num.toString()
            intent.putExtra("num", num)
        }

        val toastButton = findViewById<Button>(R.id.myButton1)
        toastButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setPositiveButton("positive") { dialog, which ->
                    num = 0
                    number.text=num.toString()
                    dialog.dismiss()
                }
                .setNegativeButton("negative") { dialog, which ->
                    dialog.dismiss()
                }
                .setNeutralButton("neutral") { dialog, which ->
                    Toast.makeText(this, "toast 메세지", Toast.LENGTH_LONG).show()
                }
                .show()
        }

        val randomButton = findViewById<Button>(R.id.myButton3)
        randomButton.setOnClickListener {
            FragmentActivity().arguments = bundle
            bundle.getString("num", num.toString())
            bundle.getInt("num", num)
            createFragm(FragmentActivity())
            createNotificationChannel()
            val intent = Intent(this, ButtonActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                builder.setContentTitle(getString(R.string.notification_title))
                builder.setContentText(getString(R.string.notification_text))
                builder.setContentIntent(pendingIntent)
                builder.setPriority(NotificationCompat.PRIORITY_MAX)
        }

    }

    companion object {
        const val CHANNEL_ID = "Test"
        const val NOTIFICATION_ID = 100
    }


    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Test notification"
            val descriptionText = "Thest notification description"
            val importance: Int = NotificationManager.IMPORTANCE_MIN
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createFragm(fragment: FragmentActivity){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}