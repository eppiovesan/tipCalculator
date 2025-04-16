package com.example.tipCalculator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text

//import com.kolydas.aboutme.databinding.ActivityMainBinding

private lateinit var edt_OutroPercentual: EditText


class tipInfoActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip_info)
        edt_OutroPercentual = findViewById<TextInputEditText>(R.id.edt_outro_percentual)


        val rb1 = findViewById<RadioButton>(R.id.rb_0)
        val rb2 = findViewById<RadioButton>(R.id.rb_10)
        val rb3 = findViewById<RadioButton>(R.id.rb_15)
        val rb4 = findViewById<RadioButton>(R.id.rb_20)
        val rb5 = findViewById<RadioButton>(R.id.rb_25)
        val rb6 = findViewById<RadioButton>(R.id.rb_Outro)

        var novo_valor:Int = 0
        var novo_valorStr: String = ""

        val edt_OutroPercentual = findViewById<TextInputEditText>(R.id.edt_outro_percentual)


        configuraRadioButtons(rb1, rb2, rb3, rb4, rb5, rb6)

        var edt_numero_pessoas = findViewById<TextInputEditText>(R.id.edt_numero_pessoas)
        edt_numero_pessoas.setText("1")

        val btn_up = findViewById<Button>(R.id.btn_up)
        btn_up.setOnClickListener {
            novo_valor = buttonUpClick(edt_numero_pessoas.text.toString().toInt())
            novo_valorStr = novo_valor.toString()

            edt_numero_pessoas.setText(novo_valorStr)
        }

        val btn_down = findViewById<Button>(R.id.btn_down)
        btn_down.setOnClickListener {
            novo_valor = buttonDownClick(edt_numero_pessoas.text.toString().toInt())
            novo_valorStr = novo_valor.toString()

            edt_numero_pessoas.setText(novo_valorStr)
        }






        val btn_calcular = findViewById<Button>(R.id.btn_calcular)

        btn_calcular.setOnClickListener {
            startActivity(Intent(this, resultActivity::class.java))

        }
    }


    private var isInternalChange = false
    private fun configuraRadioButtons(vararg radioButtons: RadioButton) {
        //ao chamar a activity passa aqui para carregar as opções
        for (i in radioButtons.indices) {
            val radio = radioButtons[i]
            radio.setOnCheckedChangeListener { buttonView, isChecked ->

                if (isInternalChange || !isChecked) return@setOnCheckedChangeListener

                // sempre passa aqui ao clicar em uma opção
                isInternalChange = true
                for (j in radioButtons.indices) {
                    // condição para deixar apenas a opção escolhida marcada
                    if (radioButtons[j] != buttonView) {
                        radioButtons[j].isChecked = false
                    }
                }

                // o edit para digitar % de gorjeta só é habilita quando a opção Outro Valor estiver marcada
                edt_OutroPercentual.isEnabled = (buttonView.id == R.id.rb_Outro)
                isInternalChange = false
            }
        }
    }


    private fun buttonUpClick (valorAtual:Int): Int {
        var novo_valor = valorAtual + 1
        return novo_valor
    }

    private fun buttonDownClick (valorAtual:Int): Int {
        var novo_valor: Int
        if (valorAtual > 1) {
            novo_valor = valorAtual - 1
        }
        else {
            novo_valor = 1
        }
        return novo_valor
    }
}





