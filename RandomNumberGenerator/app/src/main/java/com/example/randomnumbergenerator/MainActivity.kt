package com.example.randomnumbergenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_main.view.number_input_1
import kotlinx.android.synthetic.main.activity_main.view.number_input_2
import java.util.*

class MainActivity : AppCompatActivity() {
    var previous = -1
    var number1 = -1
    var number2 = -1
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val generateButton: Button = findViewById(R.id.get_number_button)
        generateButton.setOnClickListener{generateNumber()}
    }

    private fun generateNumber() {
        val n1 = number_input_1.text.toString().toInt()
        val n2 = number_input_2.text.toString().toInt()
        val numberView: TextView = findViewById(R.id.generated_number_view)

        // CHECK TO SEE IF WITHOUT REPLACEMENT CHECK BOX IS CHECKED
        if(without_replacement_check_box.isChecked) {

            // IF THE NUMBERS CHANGE THEN RESET
            if(n1 != number1 || n2 != number2) {
                number1 = n1
                number2 = n2
                count = 0
            }

            // IF THE STATE AS RESET, FIND THE NEW NUMBER TO NOT REPLACE
            if(count == 0) {
                previous = Random().nextInt((number2 - number1) + 1) + number1
                numberView.setText(previous.toString())
                count++
            }

            // GET RANDOM INTS THAT ARE NOT EQUAL TO THE NUMBER TO NOT REPLACE
            else {
                var randomInt = Random().nextInt((number2 - number1) + 1) + number1

                // IF THE RANDOM INT IS EQUAL TO THE NUMBER TO NOT REPLACE, LOOP UNTIL A DIFFERENT NUMBER IS FOUND
                while (randomInt == previous) {
                    randomInt = Random().nextInt((number2 - number1) + 1) + number1
                }
                numberView.setText(randomInt.toString())
            }
        }

        // CHECKBOX IS NOT CHECKED, ONLY FIND RANDOM INTS WITHIN RANGE
        else {
            previous = -1
            number1 = -1
            number2 = -1
            val randomInt = Random().nextInt((n2 - n1) + 1) + n1
            numberView.setText(randomInt.toString())
        }
    }
}
