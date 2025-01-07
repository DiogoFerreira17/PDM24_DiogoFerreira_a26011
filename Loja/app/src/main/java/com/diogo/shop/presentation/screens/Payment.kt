package com.diogo.shop.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.diogo.shop.presentation.viewModel.CartManagementViewModel

@Composable
fun Payment(navController: NavController,viewModel: CartManagementViewModel = viewModel()) {

    val paymentCheck by viewModel.paymentCheck.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(!paymentCheck)
        {
            Text(
                text = "Escolha o Método de Pagamento",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Button(
                onClick = { viewModel.deleteCart() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text("Pagar com Cartão")
            }

            Button(
                onClick = { viewModel.deleteCart() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text("Pagar com PayPal")
            }

            Button(
                onClick = { viewModel.deleteCart() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text("Pagar com MB Way")
            }
        }
        else{

            Text("Pagamento concluido")

            Button(
                onClick = { navController.navigate("home") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text("Voltar à loja")
            }
        }
    }
}