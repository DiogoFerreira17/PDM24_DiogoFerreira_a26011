package com.diogo.shop.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.diogo.shop.ui.viewmodel.LoginViewModel

@Composable
fun Login(
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel()
) {
    val loginState by loginViewModel.loginState.collectAsState()
    val email by loginViewModel.email.collectAsState()
    val password by loginViewModel.password.collectAsState()

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
            OutlinedTextField(
                value = email,
                onValueChange = { loginViewModel.updateEmail(it) },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = password,
                onValueChange = { loginViewModel.updatePassword(it) },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
            Button(
                onClick = { loginViewModel.login() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }

            when (loginState) {
                is LoginViewModel.LoginState.Loading -> {
                    CircularProgressIndicator()
                }
                is LoginViewModel.LoginState.Error -> {
                    Text(
                        text = (loginState as LoginViewModel.LoginState.Error).message,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                is LoginViewModel.LoginState.Success -> {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
                else -> Unit
            }

            TextButton(
                onClick = { navController.navigate("register") }
            ) {
                Text("Criar uma conta")
            }
        }
    }
}
