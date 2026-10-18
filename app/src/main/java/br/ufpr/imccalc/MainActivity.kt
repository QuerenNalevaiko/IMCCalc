package br.ufpr.imccalc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextAltura = findViewById<EditText>(R.id.etAltura)
        val editTextPeso = findViewById<EditText>(R.id.etPeso)

        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val btnLimpar = findViewById<Button>(R.id.btnLimpar)

        btnCalcular.setOnClickListener {

            // Recebe os valores digitados pelo usuario
            val alturaTexto = editTextAltura.text.toString().trim()
            val pesoTexto = editTextPeso.text.toString().trim()

            var camposValidos = true

            // Vericamos se os campos estão vazios
            if (alturaTexto.isEmpty() || pesoTexto.isEmpty()) {
                Toast.makeText(
                    this,
                    "Preencha todos os campos",
                    Toast.LENGTH_SHORT)
                    .show()
                    return@setOnClickListener
            }

            // Convertemos as virgulas para ponto e verica se é um numero
            val alturaDouble = alturaTexto.toDouble()
            val pesoDouble = pesoTexto.toDouble()

            // Verifica se a altura é um numero
            if(alturaDouble <= 0.0 || alturaDouble > 3.0) {

                editTextAltura.error = "Digite uma altura valida até 3.0m"
                camposValidos = false
            } else {
                editTextAltura.error = null
            }

            //Verifica se o peso é um numero
            if(pesoDouble <= 0.0 || pesoDouble > 300.0) {

                editTextPeso.error = "Digite um peso valido até 300kg"
                camposValidos = false
            } else {
                editTextPeso.error = null
            }

            // Interrompe apenas depois de verificar os dois campos
            if(!camposValidos) {
                return@setOnClickListener
            }

            val imcResultado = pesoDouble / (alturaDouble * alturaDouble)

            val imcCategoria = when {
                imcResultado < 18.5 -> " Abaixo do peso"
                imcResultado < 25.0 -> " Peso normal"
                imcResultado < 30.0 -> " Sobrepeso"
                imcResultado < 35.0 -> " Obesidade grau 1"
                imcResultado < 40.0 -> " Obesidade grau 2"
                else -> "Obesidade grau 3"
            }

            // Abrir a tela do resultado
            val intent = Intent(this, ResultadoActivity::class.java)
            intent.putExtra("IMC", imcResultado)
            intent.putExtra("CATEGORIA", imcCategoria)

            startActivity(intent)
        }

        // Limpar os campos para um novo calculo
        btnLimpar.setOnClickListener {
            editTextAltura.text.clear()
            editTextPeso.text.clear()

            editTextAltura.error = null
            editTextPeso.error = null

            editTextAltura.requestFocus()
        }
    }

    override fun onResume() {
        super.onResume()

        val editTextAltura = findViewById<EditText>(R.id.etAltura)
        val editTextPeso = findViewById<EditText>(R.id.etPeso)

        editTextAltura.text.clear()
        editTextPeso.text.clear()

        editTextAltura.requestFocus()
    }

}


