package com.example.tipCalculator

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class  baseActivity: AppCompatActivity() {
    protected fun setupToolbar(toolbar: Toolbar, title: String? = null, showBackButton: Boolean = false){
        setSupportActionBar(toolbar)

        // só irá excutar o código abaixo se não for nulo por conta do "?"
        supportActionBar?.apply {
            this.title = title
            //exibe ou oculta a seta de "voltar" e chama, por padrão, o metodo  onSupportNavigateUp()
            setDisplayHomeAsUpEnabled(showBackButton)
        }
    }
    // função para voltar para a tela inicial ao clicar na seta da toolbar
    //o sistema associa o clique na seta da toolbar automaticamente ao metodo onSupportNavigateUp()
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed() // comportamento padrão de voltar
        return true
    }


}