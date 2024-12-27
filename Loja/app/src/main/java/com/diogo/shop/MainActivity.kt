package com.diogo.shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diogo.shop.ui.screens.Home
import com.diogo.shop.ui.screens.Login
import com.diogo.shop.ui.screens.Register
import com.diogo.shop.ui.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseAuth.getInstance().signOut()
        setContent {
            MainFrame()
        }
    }
}

@Composable
fun MainFrame(
    loginViewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val focusManager = LocalFocusManager.current
    val navController = rememberNavController()
    val currentUser by loginViewModel.currentUser.collectAsState()

    val startDestination = if (currentUser != null) "home" else "login"

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .pointerInput(Unit) {
                    detectTapGestures { focusManager.clearFocus() }
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavHost(
                navController = navController,
                startDestination = startDestination
            ) {
                // Rotas para usuários não autenticados
                composable("login") { Login(navController) }

                composable("register") { Register(navController) }

                composable ("home" ){ Home()}

            }
        }
    }
}