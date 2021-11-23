package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ButtonActivity : AppCompatActivity() {
    val textNum = intent.getIntExtra("num", 0)
    val here = "Here is a random number between 0 to "
    val selectNum = (0..textNum!!.toInt())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)

        val justText = findViewById<TextView>(R.id.justText)

        justText.text = "Here is a random number between 0 to " + textNum

        val randomNum = findViewById<TextView>(R.id.randomNum)

        randomNum.text = selectNum.random().toString()
    }
}