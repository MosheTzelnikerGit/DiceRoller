package com.example.diceroller

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var diceImage: ImageView
    lateinit var etGuess: EditText
    lateinit var btnRoll: Button
    var rolledNumber = 1

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        diceImage = findViewById(R.id.dice_image)
        etGuess = findViewById(R.id.etGuess)
        btnRoll = findViewById(R.id.btnRoll)


        sharedPreferences = getSharedPreferences("DiceRollerPreferences", MODE_PRIVATE)


        rolledNumber = sharedPreferences.getInt("lastRoll", 1)
        updateDiceImage(rolledNumber)

        btnRoll.setOnClickListener {
            rolldice()
        }
    }

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
        val editor = sharedPreferences.edit()
        editor.putInt("lastRoll", rolledNumber)
        editor.apply()
        checkGuess()
    }

    private fun checkGuess() {
        val userGuess = etGuess.text.toString().toIntOrNull()
        if (userGuess != null && userGuess in 1..6) {
            val message = if (userGuess == rolledNumber) {
                "Correct! You guessed $userGuess!"
            } else {
                "Wrong guess! The correct number was $rolledNumber."
            }

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Guess Result")
            builder.setMessage(message)
            builder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            builder.setCancelable(false) // ניתן לשים על false כדי למנוע סגירה בטעות
            builder.show()

        } else {
            // במקרה של מספר לא תקין
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Invalid Input")
            builder.setMessage("Please enter a valid number between 1 and 6.")
            builder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            builder.setCancelable(false)
            builder.show()
        }
    }


    private fun updateDiceImage(number: Int) {
        val drawableResource = when (number) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_1
        }
        diceImage.setImageResource(drawableResource)
    }
}
