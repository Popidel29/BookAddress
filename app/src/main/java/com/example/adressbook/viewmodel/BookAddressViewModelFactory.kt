package com.example.adressbook.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.adressbook.model.repository.BookAddressRepository

class BookAddressViewModelFactory(private val repository: BookAddressRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BookAddressViewModel(repository) as T
    }

}