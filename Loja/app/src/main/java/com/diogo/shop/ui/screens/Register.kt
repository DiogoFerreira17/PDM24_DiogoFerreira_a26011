package com.diogo.shop.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.diogo.shop.domain.model.User
import com.diogo.shop.ui.viewmodel.RegisterViewModel

@Composable
fun Register(
    navController: NavController,
    viewModel: RegisterViewModel = viewModel()
) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var successMessage by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Registrar", style = MaterialTheme.typography.headlineMedium)

            OutlinedTextField(
                value = email,
                onValueChange = { viewModel.updateEmail(it) },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = password,
                onValueChange = { viewModel.updatePassword(it) },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    val user = User(email = email, password = password)
                    viewModel.register(
                        user = user,
                        onSuccess = {
                            successMessage = "Registo concluído com sucesso!"
                            navController.navigate("login")
                        },
                        onError = { exception ->
                            errorMessage = exception.message ?: "Erro desconhecido"
                        }
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar")
            }

            errorMessage?.let {
                Text(text = "Erro: $it", color = MaterialTheme.colorScheme.error)
            }

            successMessage?.let {
                Text(text = it, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
