package com.example.bmicalculator


class User(val gender: String,val weight: Double,val height: Double) {

    fun calculateBMI(): Double {
        val heightInMeters = height / 100.0
        return weight / (heightInMeters * heightInMeters)
    }


}
