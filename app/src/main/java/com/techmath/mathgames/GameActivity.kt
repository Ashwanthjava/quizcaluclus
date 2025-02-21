package com.techmath.mathgames

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Locale
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    lateinit var textScore : TextView
    lateinit var textLife : TextView
    lateinit var textTime : TextView

    lateinit var textQuestion : TextView
    lateinit var editTextAnswer : EditText

    lateinit var buttonOk : Button
    lateinit var buttonNext : Button

    var correctAnswer = 0
    var userScore = 0
    var userLife = 3

    lateinit var timer : CountDownTimer
    private val startTimerInMillis : Long = 20000
    var timeLeftInMillis : Long = startTimerInMillis

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        supportActionBar!!.title = "Addition"

        textScore = findViewById(R.id.textViewScore3)
        textLife = findViewById(R.id.textViewLifes3)
        textTime = findViewById(R.id.textViewtimes3)
        textQuestion = findViewById(R.id.textViewQuestions3)
        editTextAnswer = findViewById(R.id.editTextAnswers3)
        buttonOk = findViewById(R.id.buttonOks3)
        buttonNext = findViewById(R.id.buttoNexts3)

        gameContinue()

        buttonOk.setOnClickListener {
            val input = editTextAnswer.text.toString()

            if(input == "")
            {
                Toast.makeText(applicationContext,"please write an answer or click next",
                    Toast.LENGTH_LONG).show()

            }
            else
            {
                pauseTimer()
                val userAnswer = input.toInt()
                if (userAnswer == correctAnswer)
                {
                    userScore = userScore + 5
                    textQuestion.text = "Congratulation! your answer is correct"
                    textScore.text = userScore.toString()
                }
                else{
                    userLife = userLife-1
                    textQuestion.text = "Sorry, your answer is wrong"
                    textLife.text = userLife.toString()
                }
            }

        }
        buttonNext.setOnClickListener {

            pauseTimer()
            resetTimer()
            editTextAnswer.setText("")
            if (userLife == 0){
                Toast.makeText(applicationContext,"Game Over", Toast.LENGTH_LONG).show()
                val intent = Intent(this@GameActivity,ResultActivity::class.java)
                intent.putExtra("score",userScore)
                startActivity(intent)
                finish()

            }
            else{
                gameContinue()
            }
        }
    }

    fun gameContinue()
    {
        val number1 = Random.nextInt(10,100)
        val number2 = Random.nextInt(10,200)

        textQuestion.text = "$number1 + $number2"

        correctAnswer =number1 + number2

        startTimer()
    }

    fun startTimer(){
        timer = object : CountDownTimer(timeLeftInMillis,1000)
        {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()


                userLife--
                textLife.text = userLife.toString()
                textQuestion.text = "Sorry, Time is up!"
            }

        }.start()
    }
    fun updateText()
    {
        val remainingTime : Int = (timeLeftInMillis/1000).toInt()
        textTime.text = String.format(Locale.getDefault(),"%02d",remainingTime)
    }
    fun pauseTimer()
    {
        timer.cancel()
    }
    fun resetTimer()
    {
        timeLeftInMillis = startTimerInMillis
        updateText()
    }
    }
