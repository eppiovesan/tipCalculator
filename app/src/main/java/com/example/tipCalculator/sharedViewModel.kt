package com.example.tipCalculator

import androidx.lifecycle.ViewModel

class sharedViewModel: ViewModel() {
    var valorConta: Float = 0f
    var numeroPessoas: Int = 0
    var percentualGorjeta: Float = 0f
    var valorGorjeta: Float = 0f
    var valorFinalConta: Float = 0f


    fun calculaGorjeta(): Float {
        valorGorjeta = (valorConta * percentualGorjeta)
        return valorGorjeta
    }

    fun calculaValorFinalConta (): Float {
        if (numeroPessoas > 0){
            valorFinalConta = (valorConta + valorGorjeta)/ numeroPessoas
        }
        else {
            valorFinalConta = 0.0f
        }

        return valorFinalConta
    }

}