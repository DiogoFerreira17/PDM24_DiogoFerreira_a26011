package com.diogo.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MonotonicFrameClock
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diogo.calculadora.ui.theme.CalculadoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraTheme {
                calculator{ }
                }
            }
        }
    }

@Composable
fun calculator(onClick: () -> Unit) {

    var visor by remember { mutableStateOf("0")}
    var num1 by remember { mutableStateOf("")}
    var num2 by remember { mutableStateOf("")}
    var operator by remember { mutableStateOf("")}
    var select by remember { mutableStateOf(false)}

    fun addInput(value: String){
        if(value == "Rq"){
            num1 = "Rq"
            select = true
        }
        else if(value in listOf("+", "-", "*", "/")){

            operator = value

            if(num1.isEmpty()){ // to acept negative numbers
                num1+=value
            }
            else{
                select = true
                visor = "0"
            }

        }
        else if(!select){
            visor = ""
            num1 += value
            visor = num1
        }
        else
        {
            visor = ""
            num2 += value
            visor = num2
        }
    }

    fun ClearAll(){
        visor = "0"
        num1 = ""
        num2 = ""
        operator = ""
        select = false
    }

    fun ParcialClear(){
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

    fun Result() {
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

    Column(
        modifier = with(Modifier) { fillMaxWidth() }
            .padding(top=200.dp, start = 2.dp,end= 2.dp)
            .border(
                width = 2.dp,
                color = Color.Black
            ),
    ) {
        Text(
            text = visor,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = Color.Black
                )
                // .padding(start = 5.dp, end = 5.dp, top = 5.dp, bottom = 5.dp),
                .padding(10.dp),
            textAlign = TextAlign.Right
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp, bottom = 3.dp),

            horizontalArrangement = Arrangement.Center
        ){
            FilledTonalButton(onClick = { },
                modifier = Modifier
                .width(85.dp)
                .height(40.dp),){
                Text("MRC")
            }
            FilledTonalButton(onClick = { },
                modifier = Modifier
                    .width(85.dp)
                    .height(40.dp),) {
                Text("M-")
            }
            FilledTonalButton(onClick = { },
                modifier = Modifier
                    .width(85.dp)
                    .height(40.dp),
            ) {
                Text("M+")
            }
            Button(
                onClick = {ClearAll()},
                modifier = Modifier
                    .width(85.dp)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text("ON/C",color = Color.White)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp, bottom = 3.dp),
            horizontalArrangement = Arrangement.Center
        ){
            FilledTonalButton(
                onClick = { addInput("Rq") },
                modifier = Modifier
                .width(85.dp)
                .height(40.dp)
            ) {
                Text("Rq")
            }
            FilledTonalButton(onClick = { },
                modifier = Modifier
                    .width(85.dp)
                    .height(40.dp)) {
                Text("%")
            }
            FilledTonalButton(onClick = { },
                modifier = Modifier
                    .width(85.dp)
                    .height(40.dp)) {
                Text("+/-")
            }
            Button(
                onClick = {ParcialClear()},
                modifier = Modifier
                    .width(85.dp)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text("CE",color = Color.White)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp, bottom = 3.dp),
            horizontalArrangement = Arrangement.Center
        ){
            FilledTonalButton(onClick = {addInput("7") },
                modifier = Modifier
                    .width(85.dp)
                    .height(40.dp)){
                Text("7")
            }
            FilledTonalButton(onClick = {addInput("8") },
                modifier = Modifier
                    .width(85.dp)
                    .height(40.dp)) {
                Text("8")
            }
            FilledTonalButton(onClick = { addInput("9")},
                modifier = Modifier
                    .width(85.dp)
                    .height(40.dp)) {
                Text("9")
            }
            Button(
                onClick = { addInput("/")},
                modifier = Modifier
                    .width(85.dp)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text("/",color = Color.White)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp, bottom = 3.dp),
            horizontalArrangement = Arrangement.Center
        ){
            FilledTonalButton(onClick = { addInput("4") },
                modifier = Modifier
                    .width(85.dp)
                    .height(40.dp)) {
                Text("4")
            }
            FilledTonalButton(onClick = { addInput("5") },
                modifier = Modifier
                    .width(85.dp)
                    .height(40.dp)) {
                Text("5")
            }
            FilledTonalButton(onClick = { addInput("6") },
                modifier = Modifier
                    .width(85.dp)
                    .height(40.dp)) {
                Text("6")
            }
            Button(
                onClick = { addInput("*")},
                modifier = Modifier
                    .width(85.dp)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text("x",color = Color.White)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp, bottom = 3.dp),
            horizontalArrangement = Arrangement.Center
        ){
            FilledTonalButton(onClick = { addInput("1") },
                modifier = Modifier
                    .width(85.dp)
                    .height(40.dp)) {
                Text("1")
            }
            FilledTonalButton(onClick = { addInput("2") },
                modifier = Modifier
                    .width(85.dp)
                    .height(40.dp)) {
                Text("2")
            }
            FilledTonalButton(onClick = { addInput("3") },
                modifier = Modifier
                    .width(85.dp)
                    .height(40.dp)) {
                Text("3")
            }
            Button(
                onClick = { addInput("-") },
                modifier = Modifier
                    .width(85.dp)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text("-",color = Color.White)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp, bottom = 3.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            //Button() { }
            FilledTonalButton(onClick = { addInput("0") },
                modifier = Modifier
                    .width(85.dp)
                    .height(40.dp)) {
                Text("0")
            }
            FilledTonalButton(onClick = { addInput(".") },
                modifier = Modifier
                    .width(85.dp)
                    .height(40.dp)) {
                Text(".")
            }
            FilledTonalButton(onClick = { Result() },
                modifier = Modifier
                    .width(85.dp)
                    .height(40.dp)) {
                Text("=")
            }
            Button(
                onClick = { addInput("+") },
                modifier = Modifier
                    .width(85.dp)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text("+",color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun calculatorPreview() {
    CalculadoraTheme {
        calculator(onClick = {  })
    }
}