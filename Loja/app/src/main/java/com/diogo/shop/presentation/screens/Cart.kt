package com.diogo.shop.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.diogo.shop.presentation.viewModel.CartManagementViewModel
import androidx.compose.ui.Alignment


@Composable
fun Cart(navController: NavController, viewModel: CartManagementViewModel = viewModel()) {
    val phones by viewModel.phones.collectAsState()
    val emailToShareCart by viewModel.emailToShareCart.collectAsState()
    val cartShared by viewModel.cartShared.collectAsState()

    val groupedPhones = phones.groupBy { it.id }
    val total = groupedPhones.entries.sumOf { (_, phoneList) ->
        phoneList.first().price.toDouble() * phoneList.size
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Carrinho", style = MaterialTheme.typography.headlineMedium)

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp)
        ) {
            items(groupedPhones.entries.toList()) { (_, phoneList) ->
                val phone = phoneList.first()
                val quantity = phoneList.size

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("${phone.name} x$quantity")
                        Text("${phone.price * quantity} €")
                    }
                    Button(onClick = {
                        viewModel.removeFromCart(phone)
                    }) {
                        Text("Remover")
                    }
                }
            }
        }

        if (phones.isEmpty()) {
            Text("O carrinho está vazio.", modifier = Modifier.padding(top = 16.dp))
        } else {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Total: ${"%.2f".format(total)} €",
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 16.dp)
            )

            TextField(
                value = emailToShareCart,
                onValueChange = { viewModel.updateEmailToShareCart(it) },
                label = { Text("Email para partilhar carrinho") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            Button(
                onClick = {
                    viewModel.shareCart()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text("Partilhar Carrinho")
            }
            if (cartShared) {
                Text("Carrinho Partilhado")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    viewModel.addOrUpdateCart()
                    navController.navigate("home")
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text("Voltar à loja")
            }
            if(phones.isNotEmpty())
            {  Button(
                    onClick = {
                        navController.navigate("payment")
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    Text("Finalizar Compra")
                }
            }
        }
    }
}
