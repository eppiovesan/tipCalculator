package com.example.tipCalculator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text
import kotlin.system.exitProcess

//import com.kolydas.aboutme.databinding.ActivityMainBinding

private lateinit var edtOutroPercentual: TextInputEditText
private lateinit var edtNumeroPessoas: EditText
private lateinit var edtValorConta: EditText
private var percGorjeta: Float = 0.0f
private var outroPercGorjeta: Float = 0.0f
private var valorAtualNumeroPessoas: Int = 1
private var valorAtualOutroPercentual: Float = 0f
private lateinit var btnDown: Button
private lateinit var btnUp: Button
private lateinit var btnCalcular: Button
private var opcSelecionadaPercGorjeta = false


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
        edtNumeroPessoas = findViewById<TextInputEditText>(R.id.edt_numero_pessoas)
        edtValorConta = findViewById<TextInputEditText>(R.id.edt_valor_conta)

        //Buttons
        btnUp = findViewById<Button>(R.id.btn_up)
        btnDown = findViewById<Button>(R.id.btn_down)
        btnCalcular = findViewById<Button>(R.id.btn_calcular)

        //[Internas]
        var novoValor:Int
        var novoValorStr: String

        //Inicialização
        configuraRadioButtons(rb1, rb2, rb3, rb4, rb5, rb6)
        btnDown.isEnabled = false




        // Gorjeta
        // atribuo o valor à variável logo depois que digita alguma informação no campo
        edtOutroPercentual.addTextChangedListener { editable ->
            val novoTextoOutroPercentual = editable?.toString()
            val novoValorOutroPercentual = novoTextoOutroPercentual?.toFloatOrNull()

            if (novoValorOutroPercentual != null){
                if (novoValorOutroPercentual != valorAtualOutroPercentual){
                    valorAtualOutroPercentual = novoValorOutroPercentual
                    edtOutroPercentual.setText(valorAtualOutroPercentual.toString())
                    edtOutroPercentual.setSelection(edtOutroPercentual.length())
                }
            }
            if (valorAtualOutroPercentual > 0) {
                outroPercGorjeta = valorAtualOutroPercentual.toFloat() / 100
            } else {
                outroPercGorjeta = 0.0f
            }

            if (edtOutroPercentual.isEnabled  && edtOutroPercentual.text.toString().isNotEmpty()) {
                percGorjeta = outroPercGorjeta
            }

        }



        // Numero de pessoas
        atualizaNumeroPessoas(valorAtualNumeroPessoas)
        btnUp.setOnClickListener {
            atualizaNumeroPessoas(valorAtualNumeroPessoas + 1)
        }

        btnDown.setOnClickListener {
            if (valorAtualNumeroPessoas > 1){
                atualizaNumeroPessoas(valorAtualNumeroPessoas - 1)
            }
        }

        edtNumeroPessoas.addTextChangedListener { editable ->
            val novoTextoEdtNumeroPessoas = editable?.toString()
            val novoNumeroPessoas = novoTextoEdtNumeroPessoas?.toIntOrNull()

            if (novoNumeroPessoas != null) {
                if (novoNumeroPessoas != valorAtualNumeroPessoas) {
                    valorAtualNumeroPessoas = novoNumeroPessoas
                    btnDown.isEnabled = valorAtualNumeroPessoas > 1
                }
            } else if (novoTextoEdtNumeroPessoas.isNullOrEmpty()) {
                // Usuário apagou tudo → zera o valor
                valorAtualNumeroPessoas = 0
                btnDown.isEnabled = false
            }
        }



        // Calcular
        btnCalcular.setOnClickListener {
            // startActivity(Intent(this, resultActivity::class.java))
            println(percGorjeta)
            buttonCalcular()
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
                opcSelecionadaPercGorjeta = true
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

    private fun atualizaNumeroPessoas(novoValor:Int){
        // .coerceAtLeast garante que o novoValor não será menor do que zero
        valorAtualNumeroPessoas = novoValor.coerceAtLeast(0)
        // atribuindo o valor recebido ao editText
        edtNumeroPessoas.setText(valorAtualNumeroPessoas.toString())
        // jogando o cursor para o final do texto
        edtNumeroPessoas.setSelection(edtNumeroPessoas.text?.length?:0)
        // habilito o botão para diminuir o número
        btnDown.isEnabled = valorAtualNumeroPessoas > 1
    }



    private fun buttonCalcular(){
        var viewModel = (application as tipCalculator).sharedViewModel

        // tratar valor conta
        if (edtValorConta.text.toString().isNotEmpty()){
            if (edtValorConta.text.toString().toFloat() > 0){
                viewModel.valorConta = edtValorConta.text.toString().toFloat()
            }
            else {
                Snackbar
                    .make(
                        edtValorConta,
                        "O valor da conta deve ser maior que zero",
                        Snackbar.LENGTH_LONG
                    ).show()
                return
            }

        }
        else
        {
            Snackbar
                .make(
                    edtValorConta,
                    "O valor da conta é obrigatório",
                    Snackbar.LENGTH_LONG
                ).show()
            return
        }



        // tratar valor da gorjeta
        if (opcSelecionadaPercGorjeta){
            if ((edtOutroPercentual.isEnabled  && percGorjeta > 0) or (!edtOutroPercentual.isEnabled )){
                viewModel.percentualGorjeta = percGorjeta
            }
            else {
                Snackbar
                    .make(
                        edtOutroPercentual,
                        "O % de gorjeta deve ser maior do que zero",
                        Snackbar.LENGTH_LONG
                    ).show()
                    return
            }
        }
        else
        {
            val toast = Toast.makeText(this, "A seleção do % de gorjeta é obrigatória", Toast.LENGTH_LONG)
            toast.show()
            return
       }

       // tratar numero de pessoas
        if (edtNumeroPessoas.text.toString().isNotEmpty()){
            if (edtNumeroPessoas.text.toString().toInt() > 0 ){
                viewModel.numeroPessoas = edtNumeroPessoas.text.toString().toInt()
            }
            else{
                Snackbar
                    .make(
                        edtNumeroPessoas,
                        "O número de pessoas deve ser maior do que 0",
                        Snackbar.LENGTH_LONG
                    ).show()
            }

        }
        else{
            Snackbar
                .make(
                    edtNumeroPessoas,
                    "O número de pessoas é obrigatório",
                    Snackbar.LENGTH_LONG
                ).show()
        }


    startActivity(Intent(this, resultActivity::class.java))
    }
}





