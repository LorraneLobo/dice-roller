package com.example.diceroll.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.diceroll.R
import com.example.diceroll.domain.Dice

class MainViewModel : ViewModel() {

    val diceList = listOf(
        Dice(4, R.drawable.d4),
        Dice(6, R.drawable.dice1),
        Dice(8, R.drawable.d8),
        Dice(10, R.drawable.d10),
        Dice(12, R.drawable.d12),
        Dice(20, R.drawable.d20),
        Dice(100, R.drawable.d100),
    )

}