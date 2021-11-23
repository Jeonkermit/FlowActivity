package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val number = findViewById<TextView>(R.id.number)
        val intent = Intent(this, ButtonActivity::class.java)
        supportFragmentManager.beginTransaction().apply {

        }

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
            createFragm(FragmentActivity())
        }
    }

    fun createFragm(fragment: FragmentActivity){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}