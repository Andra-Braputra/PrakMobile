package com.example.dicexml

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var firstDice: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener {
            rollDice()
            rollDice2()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun rollDice() {
        val dice = Dice(6)
        val diceRoll = dice.roll()
        val num: TextView = findViewById(R.id.textView)
        num.text = diceRoll.toString()
        val diceImage: ImageView = findViewById(R.id.imageView)
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImage.setImageResource(drawableResource)
        firstDice = diceRoll
    }

    @SuppressLint("SetTextI18n")
    private fun rollDice2() {
        val dice = Dice(6)
        val diceRoll = dice.roll()
        val num: TextView = findViewById(R.id.textView2)
        num.text = diceRoll.toString()
        val diceImage: ImageView = findViewById(R.id.imageView2)
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImage.setImageResource(drawableResource)-

        if(firstDice == diceRoll){
            Toast.makeText(this, "Selamat, anda dapat dadu double!", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(this, "Anda belum beruntung!", Toast.LENGTH_SHORT).show()
        }
    }

}



class Dice(private val numSides : Int){
    fun roll() : Int{
        return (1..numSides).random()
    }
}