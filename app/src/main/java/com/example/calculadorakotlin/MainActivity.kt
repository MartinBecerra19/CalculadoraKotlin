package com.example.calculadorakotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var num1 = 0.0
    private var num2 = 0.0
    private var operacion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        twResultado.text = "0"
        operacion = SinOperacion

        //Evento click botones números
        btnCero.setOnClickListener { numeroPresionado("0") }
        btnUno.setOnClickListener { numeroPresionado("1") }
        btnDos.setOnClickListener { numeroPresionado("2") }
        btnTres.setOnClickListener { numeroPresionado("3") }
        btnCuatro.setOnClickListener { numeroPresionado("4") }
        btnCinco.setOnClickListener { numeroPresionado("5") }
        btnSeis.setOnClickListener { numeroPresionado("6") }
        btnSiete.setOnClickListener { numeroPresionado("7") }
        btnOcho.setOnClickListener { numeroPresionado("8") }
        btnNueve.setOnClickListener { numeroPresionado("9") }

        //Evento click botón punto
        btnPunto.setOnClickListener { numeroPresionado(".") }

        //Evento click botones operaciones
        btnSumar.setOnClickListener { operacionPresionada(Suma) }
        btnRestar.setOnClickListener { operacionPresionada(Resta) }
        btnMultiplicar.setOnClickListener { operacionPresionada(Multiplicacion) }
        btnDividir.setOnClickListener { operacionPresionada(Division) }

        //Evento click botones borrar e igual
        btnBorrar.setOnClickListener { borrarTexto() }
        btnIgual.setOnClickListener { resolverOperacion() }
    }

    //Constantes de operadores
    companion object {
        const val Suma = 1
        const val Resta = 2
        const val Multiplicacion = 3
        const val Division = 4
        const val SinOperacion = 0
    }

    private fun numeroPresionado(num: String) {

        if (twResultado.text == "0" && num != ".") { //Si el texto de resultado es 0 y el dígito pulsado no es el punto
            twResultado.text = "$num"
        } else {
            println("${twResultado.text}$num")
            twResultado.text = "${twResultado.text}$num"
        }

        if (operacion == SinOperacion) { //Si todavía no se ha realizado ninguna operación
            num1 = twResultado.text.toString().toDouble() //Todavía estamos poniendo el primer dígito
        } else { //Sino...
            num2 = twResultado.text.toString().toDouble() //Ya estamos en el segundo dígito
        }

    }

    private fun operacionPresionada(operacion: Int) {
        this.operacion = operacion
        num1 = twResultado.text.toString().toDouble()
        twResultado.text = "0"
    }

    private fun borrarTexto() {
        twResultado.text = "0"
        num1 = 0.0
        num2 = 0.0
    }

    private fun resolverOperacion() {
        var resultado = when (operacion) {
            Suma -> num1 + num2
            Resta -> num1 - num2
            Multiplicacion -> num1 * num2
            Division -> num1 / num2
            else -> 0
        }

        num1 = resultado as Double

        if ("$resultado".endsWith(".0")) { //Si el resultado no es decimal
            "$resultado".replace(".0", "") //Quitamos el .0 del double
        } else {
            "%.2f".format(resultado)
        }

        twResultado.text = "$resultado"
    }
}