package com.example.tipCalculator

import androidx.lifecycle.ViewModel

class sharedViewModel: ViewModel() {
    var numero1: Float = 0f
    var numero2: Float = 0f


    fun getSoma(): Float {
        var resultado: Float = numero1 + numero2
        return resultado
    }
}