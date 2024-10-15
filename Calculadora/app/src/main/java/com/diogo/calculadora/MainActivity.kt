package com.diogo.calculadora
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.diogo.calculadora.ui.CalculatorBody
import com.diogo.calculadora.ui.theme.CalculadoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraTheme {
                CalculatorBody()
                }
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun calculatorPreview() {
    CalculadoraTheme {
        CalculatorBody()
    }
}