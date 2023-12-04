package com.example.diceroll.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diceroll.R
import com.example.diceroll.domain.Dice
import com.example.diceroll.domain.DiceResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val diceList = listOf(
        Dice(6, R.drawable.dice_1),
        Dice(4, R.drawable.d4),
        Dice(8, R.drawable.d8),
        Dice(10, R.drawable.d10),
        Dice(12, R.drawable.d12),
        Dice(20, R.drawable.d20),
        Dice(100, R.drawable.d100),
    )

    private val _selectedDice: MutableStateFlow<Dice> = MutableStateFlow(diceList.first())
    val selectedDice: StateFlow<Dice> = _selectedDice

    private val _diceResult: MutableStateFlow<Int?> = MutableStateFlow(null)
    val diceResult: StateFlow<Int?> = _diceResult

    private val _diceHistory: MutableStateFlow<List<DiceResult>> = MutableStateFlow(listOf())
    val diceHistory: StateFlow<List<DiceResult>> = _diceHistory

    fun selectDice(dice: Dice) {
        viewModelScope.launch {
            _selectedDice.emit(dice)
            _diceResult.emit(null)
        }
    }

    fun rollDice() {
        viewModelScope.launch {
            val value = (1..selectedDice.value.faces).random()
            _diceHistory.emit(_diceHistory.value.plus(DiceResult(value, selectedDice.value)))
            _diceResult.emit(value)
        }
    }
}