package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var diceImage: ImageView
    lateinit var etGuess: EditText
    lateinit var btnRoll: Button
    var rolledNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // איתור רכיבי הממשק
        diceImage = findViewById(R.id.dice_image)
        etGuess = findViewById(R.id.etGuess)
        btnRoll = findViewById(R.id.btnRoll)

        // ברגע שנלחץ על כפתור Roll, תתבצע הטלת קובייה
        btnRoll.setOnClickListener {
            rolldice()
        }
    }

    // פונקציה להטלת קובייה
    private fun rolldice() {
        rolledNumber = (1..6).random()
        val drawableResource = when (rolledNumber) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImage.setImageResource(drawableResource)
        // השוואת ניחוש המשתמש עם התוצאה מהקובייה
        checkGuess()
    }

    // פונקציה להשוואת ניחוש המשתמש עם התוצאה מהקובייה
    private fun checkGuess() {
        val userGuess = etGuess.text.toString().toIntOrNull()

        if (userGuess != null && userGuess in 1..6) {
            if (userGuess == rolledNumber) {
                Toast.makeText(this, "Correct! You guessed $userGuess!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Wrong guess! The correct number was $rolledNumber.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please enter a valid number between 1 and 6.", Toast.LENGTH_SHORT).show()
        }
    }
}
