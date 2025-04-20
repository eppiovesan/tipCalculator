package com.example.tipCalculator
import android.app.Application

class tipCalculator: Application(){
    lateinit var sharedViewModel: sharedViewModel

    override fun onCreate(){
        super.onCreate()
        sharedViewModel = sharedViewModel()
    }
}