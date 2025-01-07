package com.diogo.shop.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diogo.shop.data.remote.api.Firebase
import com.diogo.shop.data.repository.PhonesRepositoryImpl
import com.diogo.shop.domain.model.Phone
import com.diogo.shop.domain.use_case.GetAllPhonesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PhonesViewModel : ViewModel() {

    private val _phones = MutableStateFlow<List<Phone>>(emptyList())
    val phones: StateFlow<List<Phone>> = _phones

    private val db = Firebase.firestore
    private val phonesRepository = PhonesRepositoryImpl(db)
    private val getAllPhonesUseCase = GetAllPhonesUseCase(phonesRepository)

    init {
        loadPhones()
    }

    private fun loadPhones() {
        viewModelScope.launch {
            try {
                _phones.value = getAllPhonesUseCase()
            } catch (e: Exception) {
                Log.e("Erro ao carregar a lista de telemoveis",_phones.value.toString())
            }
        }
    }
}
