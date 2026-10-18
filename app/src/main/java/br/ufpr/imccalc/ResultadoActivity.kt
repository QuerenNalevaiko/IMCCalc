package br.ufpr.imccalc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class ResultadoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val tvSeuImc = findViewById<TextView>(R.id.tvSeuImc)
        val tvCategoria = findViewById<TextView>(R.id.tvCategoria)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        val imc = intent.getDoubleExtra("IMC", 0.0)
        val categoria = intent.getStringExtra("CATEGORIA") ?: ""

        // Recebe os dados da MainActivity
        tvSeuImc.text = "Seu IMC é: %.2f".format(imc)
        tvCategoria.text = categoria

        // Volta pra a tela principal
        btnVoltar.setOnClickListener {
            finish()
        }
    }
}
