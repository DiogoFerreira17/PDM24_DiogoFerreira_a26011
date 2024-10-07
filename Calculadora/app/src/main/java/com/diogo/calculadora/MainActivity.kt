package com.diogo.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MonotonicFrameClock
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun calculator(onClick: () -> Unit): Unit {

    //var visor = remember {
      //  mutableStateOf(currentNumber)
    //}

    //Visor
    var visor by remember { mutableStateOf("0") }

    Column {
        Text(
            text= visor,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        )

        Row {
            FilledTonalButton(onClick = { visor += "7" }) {
                Text("7")
            }
            FilledTonalButton(onClick = { visor += "8" }) {
                Text("8")
            }
            FilledTonalButton(onClick = { visor += "9" }) {
                Text("9")
            }
            //Button(onClick = { onClick() }) {
                //Text("/")
            //}
            Button(
                onClick = { visor += "/" },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black) // Cor de fundo preto
            ) {
                Text("/", color = Color.White) // Texto em branco para contraste
            }
        }
        Row {
            FilledTonalButton(onClick = { visor += "4" }) {
                Text("4")
            }
            FilledTonalButton(onClick = { visor += "5" }) {
                Text("5")
            }
            FilledTonalButton(onClick = { visor += "6" }) {
                Text("6")
            }
            Button(
                onClick = { visor += "x" },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black) // Cor de fundo preto
            ) {
                Text("x", color = Color.White) // Texto em branco para contraste
            }
        }
        Row {
            FilledTonalButton(onClick = { visor += "1" }) {
                Text("1")
            }
            FilledTonalButton(onClick = { visor += "2" }) {
                Text("2")
            }
            FilledTonalButton(onClick = { visor += "3" }) {
                Text("3")
            }
            Button(
                onClick = { visor += "-" },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black) // Cor de fundo preto
            ) {
                Text("-", color = Color.White) // Texto em branco para contraste
            }
        }
        Row {
            //Button() { }
            FilledTonalButton(onClick = { visor += "0" }) {
                Text("0")
            }
            FilledTonalButton(onClick = { onClick() }) {
                Text(".")
            }
            FilledTonalButton(onClick = { onClick() }) {
                Text("=")
            }
            Button(
                onClick = { visor += "+" },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black) // Cor de fundo preto
            ) {
                Text("+", color = Color.White) // Texto em branco para contraste
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun calculatorPreview() {
    CalculadoraTheme {
        calculator(onClick = { /* Define what happens on click here */ })
    }
}