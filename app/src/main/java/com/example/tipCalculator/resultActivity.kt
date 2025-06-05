package com.example.tipCalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class resultActivity: baseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val viewModel = (application as tipCalculator).sharedViewModel

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setupToolbar(toolbar, "Calculadora de Gorjeta", true)


        val valorConta = viewModel.valorConta
        val numeroPessoas = viewModel.numeroPessoas
        val percentualGorjeta = (viewModel.percentualGorjeta) * 100
        val valorGorjeta = String.format("%.2f", viewModel.calculaGorjeta()).toFloat()
        val valorPessoa = String.format("%.2f", viewModel.calculaValorFinalConta()).toFloat()

        val tvValorConta = findViewById<TextView>(R.id.txt_valor_conta)
        val tvNumeroPessoas = findViewById<TextView>(R.id.txt_qtde_pessoas)
        val tvPercentualGorjeta = findViewById<TextView>(R.id.txt_percentual_gorjeta)
        val tvValorGorjeta = findViewById<TextView>(R.id.txt_valor_gorjeta)
        val tvValorPorPessoa = findViewById<TextView>(R.id.text_result)

        val btnRecalcular = findViewById<Button>(R.id.btn_reacalcular)

        tvValorConta.text = valorConta.toString()
        tvNumeroPessoas.text = numeroPessoas.toString()
        tvPercentualGorjeta.text = percentualGorjeta.toString()
        tvValorGorjeta.text = valorGorjeta.toString()
        tvValorPorPessoa.text = valorPessoa.toString()

        btnRecalcular.setOnClickListener {
            startActivity(Intent(this,tipInfoActivity::class.java))
            finish()
        }
}



}