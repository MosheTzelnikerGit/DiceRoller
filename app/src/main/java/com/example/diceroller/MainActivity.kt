package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvDiceResult: TextView = findViewById(R.id.tvDiceResult)
        val btnRoll: Button = findViewById(R.id.btnRoll)
        btnRoll.text = "Let's Roll"


        btnRoll.setOnClickListener {
            Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show()

            val randomDiceRoll = (1..6).random()
            tvDiceResult.text = randomDiceRoll.toString()
        }
    }
}
