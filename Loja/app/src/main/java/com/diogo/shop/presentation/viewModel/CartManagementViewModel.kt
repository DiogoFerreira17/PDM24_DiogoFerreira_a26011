package com.diogo.shop.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diogo.shop.data.remote.api.Firebase
import com.diogo.shop.data.remote.model.CartDto
import com.diogo.shop.domain.model.Phone
import com.diogo.shop.data.repository.CartsRepositoryImpl
import com.diogo.shop.domain.model.Cart
import com.diogo.shop.domain.repository.CartsRepository
import com.diogo.shop.domain.use_case.AddOrUpdateCartUseCase
import com.diogo.shop.domain.use_case.DeleteCartUseCase
import com.diogo.shop.domain.use_case.GetCartByEmailUseCase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartManagementViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    private val _phones = MutableStateFlow<List<Phone>>(emptyList())
    private val _cart = MutableStateFlow<Cart?>(null)
    private val _emailToShareCart = MutableStateFlow("")
    private val _cartShared = MutableStateFlow<Boolean>(false)
    private val _paymentCheck = MutableStateFlow<Boolean>(false)

    val email: StateFlow<String> = _email
    val phones: StateFlow<List<Phone>> = _phones
    val emailToShareCart: StateFlow<String> = _emailToShareCart

    val cartShared: StateFlow<Boolean> = _cartShared
    val paymentCheck: StateFlow<Boolean> = _paymentCheck

    private val db = Firebase.firestore
    private val cartsRepository = CartsRepositoryImpl(db)
    private val addOrUpdateCartUseCase = AddOrUpdateCartUseCase(cartsRepository)
    private val getCartByEmailUseCase = GetCartByEmailUseCase(cartsRepository)
    private val deleteCartUseCase = DeleteCartUseCase(cartsRepository)

    init {
        _email.value = FirebaseAuth.getInstance().currentUser?.email ?: ""
        loadCartByEmail()
    }

    fun addToCart(phone: Phone) {
        val currentPhones = _phones.value.toMutableList()
        currentPhones.add(phone)
        _phones.value = currentPhones
    }

    fun removeFromCart(phone: Phone) {
        val currentPhones = _phones.value.toMutableList()
        currentPhones.remove(phone)
        _phones.value = currentPhones

        viewModelScope.launch {
            try {
                val cart = CartDto(
                    email = _email.value,
                    phones = currentPhones
                )
                addOrUpdateCartUseCase(cart)
            } catch (e: Exception) {
                Log.e("Delete","Erro ao remover telemovel")
            }
        }
    }

    private fun loadCartByEmail() {
        viewModelScope.launch {
            val result = getCartByEmailUseCase(email.value)
            result?.let {
                _cart.value = it
                _phones.value = it.phones
            }
        }
    }

    fun updateEmailToShareCart(email: String){
        _emailToShareCart.value = email
    }

    fun shareCart() {
        viewModelScope.launch {
            try {
                if (_emailToShareCart.value.isNotEmpty() && _phones.value.isNotEmpty()) {
                    val cart = CartDto(
                        email = emailToShareCart.value,
                        phones = phones.value
                    )

                    val status = addOrUpdateCartUseCase(cart)
                    if (status is CartsRepository.AddOrUpdateCartStatus.Success) {
                        _cartShared.value = true
                        Log.e("Partilha","Carrinho partilhado com sucesso")
                    } else {
                        Log.e("Partilha","Erro ao partilhar carrinho")
                    }
                }
            } catch (e: Exception) {
                Log.e("Partilha","Erro ao partilhar carrinho")
            }
        }
    }

    fun addOrUpdateCart() {
        viewModelScope.launch {
            try {
                if (_email.value.isNotEmpty() && _phones.value.isNotEmpty()) {
                    val cart = CartDto(
                        email = email.value,
                        phones =_phones.value
                    )

                    val status = addOrUpdateCartUseCase(cart)
                    if (status is CartsRepository.AddOrUpdateCartStatus.Success) {
                        Log.e("AddOrUpdateCart","Carrinho criado ou atualizado com sucesso")
                    } else {
                        Log.e("AddOrUpdateCart","Erro ao criar ou atualizar")
                    }
                }
            } catch (e: Exception) {
                Log.e("AddOrUpdateCart","Erro ao criar ou atualizar")
            }
        }
    }

    fun deleteCart() {
        viewModelScope.launch {
            try {
                val email = _email.value
                if (email.isNotEmpty()) {
                    val result = deleteCartUseCase(email)
                    if (result) {
                        _phones.value = emptyList()
                        _paymentCheck.value = true
                        Log.d("Delete", "Carrinho eliminado com sucesso")
                    } else {
                        Log.e("Delete", "Erro ao eliminar carrinho")
                    }
                }
            } catch (e: Exception) {
                Log.e("Delete", "Erro ao eliminar carrinho")
            }
        }
    }

}