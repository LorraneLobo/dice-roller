package com.example.diceroll

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.diceroll.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var actualDice = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.addD20Dice.setOnClickListener {
            actualDice = 20
            binding.imgDice.setImageResource(R.drawable.d20)
        }
        binding.addD6ClassicDice.setOnClickListener {
            actualDice = 6
            rollClassicD6Dice()
        }
        binding.btnRollDice.setOnClickListener {
            val maxNumber = actualDice
            if (maxNumber == 6 ) {
                rollClassicD6Dice()
            } else {
                val value = (1..maxNumber).random()
                binding.tvDiceNumber.visibility = View.VISIBLE
                binding.tvDiceNumber.text = "$value"
            }
        }
    }

    private fun rollClassicD6Dice() {
        val value = (1..6).random()
        binding.tvDiceNumber.visibility = View.GONE
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
