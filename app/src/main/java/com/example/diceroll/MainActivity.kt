package com.example.diceroll

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diceroll.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rollDice()
    }

    private fun rollDice() {
        binding.btnRollDice.setOnClickListener {
            val value = (1..6).random()
            changeDiceImage(value)
        }
    }

    private fun changeDiceImage(value: Int) {
        when (value) {
            1 -> binding.imgDice.setImageResource(R.drawable.dice1)
            2 -> binding.imgDice.setImageResource(R.drawable.dice2)
            3 -> binding.imgDice.setImageResource(R.drawable.dice3)
            4 -> binding.imgDice.setImageResource(R.drawable.dice4)
            5 -> binding.imgDice.setImageResource(R.drawable.dice5)
            6 -> binding.imgDice.setImageResource(R.drawable.dice6)
            else -> binding.imgDice.setImageResource(R.drawable.dice1)
        }

    }
}