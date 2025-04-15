package com.example.tipCalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class resultActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)


        val btn_recalcular = findViewById<Button>(R.id.btn_reacalcular)

        btn_recalcular.setOnClickListener {
            startActivity(Intent(this,tipInfoActivity::class.java))
        }
}



}