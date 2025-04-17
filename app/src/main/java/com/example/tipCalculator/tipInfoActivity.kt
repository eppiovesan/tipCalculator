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
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text
//import com.kolydas.aboutme.databinding.ActivityMainBinding

private lateinit var edtOutroPercentual: EditText
private var percGorjeta: Float = 0.0f
private var outroPercGorjeta: Float = 0.0f


class tipInfoActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip_info)

        // Variáveis locais + Atribuições

        //[Componentes]

        //Radio button
        val rb1 = findViewById<RadioButton>(R.id.rb_0)
        val rb2 = findViewById<RadioButton>(R.id.rb_10)
        val rb3 = findViewById<RadioButton>(R.id.rb_15)
        val rb4 = findViewById<RadioButton>(R.id.rb_20)
        val rb5 = findViewById<RadioButton>(R.id.rb_25)
        val rb6 = findViewById<RadioButton>(R.id.rb_Outro)



        //Input Text
        edtOutroPercentual = findViewById<TextInputEditText>(R.id.edt_outro_percentual)
        val edtNumeroPessoas = findViewById<TextInputEditText>(R.id.edt_numero_pessoas)
        val edtValorConta = findViewById<TextInputEditText>(R.id.edt_valor_conta)

        //Buttons
        val btnUp = findViewById<Button>(R.id.btn_up)
        val btnDown = findViewById<Button>(R.id.btn_down)
        val btnCalcular = findViewById<Button>(R.id.btn_calcular)

        //[Internas]
        var novoValor:Int = 0
        var novoValorStr: String = ""



        //Inicialização
        configuraRadioButtons(rb1, rb2, rb3, rb4, rb5, rb6)
        edtNumeroPessoas.setText("1")


        // Ações
        btnUp.setOnClickListener {
            novoValor = buttonUpClick(edtNumeroPessoas.text.toString().toInt())
            novoValorStr = novoValor.toString()

            edtNumeroPessoas.setText(novoValorStr)
        }


        btnDown.setOnClickListener {
            novoValor = buttonDownClick(edtNumeroPessoas.text.toString().toInt())
            novoValorStr = novoValor.toString()

            edtNumeroPessoas.setText(novoValorStr)
        }

        btnCalcular.setOnClickListener {
           // startActivity(Intent(this, resultActivity::class.java))
            println(percGorjeta)
        }


        // atribuo o valor à variável logo depois que digita alguma informação no campo
        edtOutroPercentual.addTextChangedListener {
            val texto = it.toString()
            if (texto.isNotEmpty()) {
                outroPercGorjeta = texto.toFloat() / 100
            } else {
                outroPercGorjeta = 0.0f
            }
        }


    }


    //Funções
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
                edtOutroPercentual.isEnabled = (buttonView.id == R.id.rb_Outro)

                // atribuindo valor à variável perc_gorjeta de acordo com o radio button selecionado
                    percGorjeta = when (buttonView.id){
                        R.id.rb_0 -> 0.0f
                        R.id.rb_10 -> 0.10f
                        R.id.rb_15 -> 0.15f
                        R.id.rb_20 -> 0.20f
                        R.id.rb_25 -> 0.25f

                        R.id.rb_Outro -> {
                            // Quando o "Outro" for selecionado, habilita o campo de texto
                            edtOutroPercentual.isEnabled = true
                            // mantenho perc_gorjeta até o input text ser preenchido
                            outroPercGorjeta
                        }
                        else -> 0.0f
                    }
                    if (edtOutroPercentual.isEnabled  && edtOutroPercentual.text.toString().isNotEmpty()) {
                        percGorjeta = outroPercGorjeta
                    }

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





