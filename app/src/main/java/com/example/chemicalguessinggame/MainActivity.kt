package com.example.chemicalguessinggame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var addInputEditText: EditText
    private lateinit var guessInputEditText: EditText
    private lateinit var statusTextView: TextView
    private lateinit var addButton: Button
    private lateinit var guessButton: Button
    private lateinit var chemicalImageView: ImageView
    private val chemicalList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addInputEditText = findViewById(R.id.addInputEditText)
        guessInputEditText = findViewById(R.id.guessInputEditText)
        statusTextView = findViewById(R.id.statusTextView)
        addButton = findViewById(R.id.addButton)
        guessButton = findViewById(R.id.guessButton)
        chemicalImageView = findViewById(R.id.chemicalImageView)

        addButton.setOnClickListener {
            addChemical(addInputEditText.text.toString().trim())
        }

        guessButton.setOnClickListener {
            guessChemical(guessInputEditText.text.toString().trim())
        }
    }

    private fun addChemical(newChemical: String) {
        if (newChemical.isNotEmpty()) {
            if (!chemicalList.contains(newChemical)) {
                chemicalList.add(newChemical)
                statusTextView.text = "Chemical '$newChemical' added."
            } else {
                statusTextView.text = "Chemical '$newChemical' is already available."
            }
        } else {
            statusTextView.text = "Please enter a valid chemical name."
        }
        addInputEditText.text.clear()
    }

    private fun guessChemical(userGuess: String) {
        if (chemicalList.isEmpty()) {
            statusTextView.text = "No chemical names available to guess."
            return
        }
        val randomChemical = chemicalList.random()
        if (userGuess.isNotEmpty()) {
            if (userGuess.equals(randomChemical, ignoreCase = true)) {
                statusTextView.text = "Congratulations! You guessed it right."
            } else {
                statusTextView.text = "Sorry, wrong guess. The correct answer was '$randomChemical'."
            }
        } else {
            statusTextView.text = "Please enter your guess."
        }
        guessInputEditText.text.clear()
    }
}