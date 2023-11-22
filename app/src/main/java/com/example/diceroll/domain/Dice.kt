package com.example.diceroll.domain

import androidx.annotation.DrawableRes

data class Dice (
    val faces: Int,
    @DrawableRes val image: Int
)