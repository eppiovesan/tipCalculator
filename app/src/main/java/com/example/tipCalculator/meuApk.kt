package com.example.tipCalculator
import android.app.Application

class meuApk: Application(){
    lateinit var sharedViewModel: sharedViewModel

    override fun onCreate(){
        super.onCreate()
        sharedViewModel = sharedViewModel()
    }
}