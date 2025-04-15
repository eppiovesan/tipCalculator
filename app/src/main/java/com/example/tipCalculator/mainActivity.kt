package com.example.tipCalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText

class MainActivity: AppCompatActivity (){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonIniciar()
    }

    private fun buttonIniciar() {

        val btn_iniciar= findViewById<Button>(R.id.btn_iniciar)
        btn_iniciar.setOnClickListener {
            startActivity(Intent(this, tipInfoActivity::class.java))
        }
    }
}