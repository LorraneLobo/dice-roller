package com.example.diceroll.presentation.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.example.diceroll.R
import com.example.diceroll.databinding.ActivityMainBinding
import com.example.diceroll.presentation.viewmodel.MainViewModel
import com.nambimobile.widgets.efab.FabOption

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListeners()
        configDicesView()
    }

    private fun setClickListeners() {
        binding.btnRollDice.setOnClickListener {
            if (viewModel.selectedDice.value.faces == 6) {
                rollClassicD6Dice()
            } else {
                val value = (1..viewModel.selectedDice.value.faces).random()
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

    private fun configDicesView() {
        viewModel.diceList.forEach { dice ->
            val fab = FabOption(context = this).apply {
                label.labelText = "Rolar D${dice.faces}"
                fabOptionIcon = AppCompatResources.getDrawable(this@MainActivity, dice.image)
                setOnClickListener {
                    viewModel.selectDice(dice)
                    binding.tvDiceNumber.text = " "
                    binding.imgDice.setImageResource(dice.image)
                }
            }
            binding.diceButtons.addView(fab)
        }
    }
}
