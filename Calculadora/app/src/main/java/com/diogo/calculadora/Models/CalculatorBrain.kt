package com.diogo.calculadora.Models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

var visor by mutableStateOf("0")
var num1 by mutableStateOf("")
var num2 by mutableStateOf("")
var operator by mutableStateOf("")
var select by mutableStateOf(false)

fun addInput(value: String) {
    if (value == "Rq") {
        num1 = value
        select = true
    } else if (value in listOf("+", "-", "*", "/")) {
        operator = value

        if (num1.isEmpty()) { // aceitar números negativos
            num1 += value
        } else {
            select = true
        }

    } else if (!select) {
        num1 += value
        visor = num1
    } else {
        if (visor == num1) {  // Garante que só limpa o visor ao iniciar o segundo número
            visor = "0"
        }
        num2 += value
        visor = num2
    }
}

fun clearAll(){
    visor = "0"
    num1 = ""
    num2 = ""
    operator = ""
    select = false
}

fun parcialClear(){
    if(operator.isEmpty() && num2.isEmpty()){
        num1 = ""
        visor = "0"
    }
    else if(num1.isNotEmpty() && num2.isEmpty()){
        operator = ""
        visor = "0"
    }
    else{
        num2 = ""
        visor = "0"
    }
}

fun formatResult(result: Double): String {
    return if (result % 1.0 == 0.0) {
        result.toInt().toString()
    } else {
        result.toString()
    }
}

fun result() {
    val number1 = num1.toDoubleOrNull() // returns string as a number, or null if the string is not a valid representation of a number
    val number2 = num2.toDoubleOrNull()

    if(num1 == "Rq" && number2 !=null && number2 > 0 ) {
        number2.toInt()
        val result = kotlin.math.sqrt(number2)
        visor = formatResult(result)
    }
    else if (number1 != null && number2 != null) {   // Dúvida !!!
        val result = when (operator) {
            "+" -> number1 + number2
            "-" -> number1 - number2
            "*" -> number1 * number2
            "/" -> if (number2 != 0.0){
                number1 / number2
            }
            else Double.NaN // not a number
            else -> Double.NaN
        }
        visor = formatResult(result)
    }

    num1 = visor  // to do multiple accounts
    num2 = ""
    operator = ""
    select = false

}