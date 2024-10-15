package com.diogo.calculadora.ui
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.diogo.calculadora.Models.clearAll
import com.diogo.calculadora.Models.parcialClear
import com.diogo.calculadora.Models.result
import com.diogo.calculadora.Models.addInput
import com.diogo.calculadora.Models.visor



@Composable
fun CalculatorBody(){

    Column(
        modifier = with(Modifier) { fillMaxWidth() }
            .padding(top=200.dp, start = 2.dp,end= 2.dp)
            .border(
                width = 2.dp,
                color = Color.Black
            ),
    ) {
        Screen(visor)

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
                onClick = {clearAll()},
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
                Text("√")
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
                onClick = {parcialClear()},
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
            FilledTonalButton(onClick = { result() },
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