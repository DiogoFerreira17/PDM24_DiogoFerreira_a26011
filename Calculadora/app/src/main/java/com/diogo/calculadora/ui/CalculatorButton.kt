package com.diogo.calculadora.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CustomButton(text: String, onclick: (String)->Unit){
    Button(
        onClick = { onclick(text)}
    ) {
        Text(text)
    }
}