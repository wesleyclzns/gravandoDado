package br.com.gravandodados

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //Propriedade de classe
    var acessoSharedPref : SharedPreferences? = null
    var editorSharedPref : SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Atribundo valores aos objetos do SharedPref
        acessoSharedPref = getSharedPreferences("meusDados", Context.MODE_PRIVATE)
        editorSharedPref = acessoSharedPref!!.edit()

        //Gravando dados
        btnGravar.setOnClickListener {

            //Verificando se o campo esta preenchito

            if (edtDado.text.isNotEmpty()){
                val textoDigitado = edtDado.text.toString()

                //Gravando Texto

                editorSharedPref!!.putString("dado",textoDigitado)
                editorSharedPref!!.apply()

                edtDado.text.clear()
                Toast.makeText(this, "Dado gravado com Sucesso", Toast.LENGTH_LONG).show()

            } else{
                Toast.makeText(this, "Preencha o campo corretamente", Toast.LENGTH_LONG).show()
            }

        }

        //Carregando dados gravados
        btnCarregar.setOnClickListener {

            //verificando se existe dado gravado
            if (acessoSharedPref!!.contains("dado")){

                //Resgatando dado
                val dadoResgatado = acessoSharedPref!!.getString("dado", "")
                txvResultado.text = dadoResgatado
            } 

        }
    }
}
