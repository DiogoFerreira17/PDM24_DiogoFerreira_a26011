import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.diogo.shop.domain.model.Phone
import com.diogo.shop.presentation.viewModel.CartManagementViewModel
import com.diogo.shop.presentation.viewModel.PhonesViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Home(
    navController: NavController,
    phonesViewModel: PhonesViewModel = viewModel(),
    cartViewModel: CartManagementViewModel = viewModel()
) {
    val phones by phonesViewModel.phones.collectAsState()
    val cartPhones by cartViewModel.phones.collectAsState()

    BackHandler { navController.navigate("home") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = "Loja de Telemóveis",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 8.dp)
            )

            IconButton(
                onClick = { navController.navigate("cart")
                          cartViewModel.addOrUpdateCart()},
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                BadgedBox(badge = {
                    if (cartPhones.isNotEmpty()) {
                        Badge {
                            Text(cartPhones.size.toString())
                        }
                    }
                }) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = "Carrinho")
                }
            }
        }

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(phones) { phone: Phone ->
                PhoneItem(phone, cartViewModel)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { logout(navController) }) {
            Text(text = "Terminar Sessão")
        }
    }
}

@Composable
fun PhoneItem(phone: Phone, cartManagementViewModel: CartManagementViewModel) {
    var quantity by remember { mutableStateOf(0) }  // Inicializa com 0

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = phone.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Preço: ${phone.price} €", style = MaterialTheme.typography.bodyMedium)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { if (quantity > 0) quantity-- }) {
                    Text("-")
                }
                Text("$quantity", style = MaterialTheme.typography.bodyLarge)
                IconButton(onClick = { quantity++ }) {
                    Text("+")
                }
            }

            if (quantity > 0) {
                Button(
                    onClick = {
                        repeat(quantity) {
                            cartManagementViewModel.addToCart(phone)
                        }
                        quantity = 0
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Adicionar ao Carrinho")
                }
            }
        }
    }
}

fun logout(navController: NavController) {
    FirebaseAuth.getInstance().signOut()
    navController.navigate("login") {
        popUpTo("home") { inclusive = true }
    }
}