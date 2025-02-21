package com.techmath.mathgames

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var addition : Button
    lateinit var subtraction : Button
    lateinit var multi : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000L)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addition = findViewById(R.id.buttonadd)
        subtraction = findViewById(R.id.buttonsub)
        multi = findViewById(R.id.buttonmul)

        addition.setOnClickListener {
            val intent = Intent(this@MainActivity,GameActivity::class.java)
            startActivity(intent)
        }
        subtraction.setOnClickListener {
            val intent = Intent(this@MainActivity,Game2Activity::class.java)
            startActivity(intent)
        }
        multi.setOnClickListener {
           val intent = Intent(this@MainActivity,Game3Activity::class.java)
            startActivity(intent)
        }
    }
}