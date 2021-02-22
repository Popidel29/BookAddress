package com.example.adressbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adressbook.model.db.BookAddressEntity
import com.example.adressbook.model.repository.BookAddressRepository
import io.reactivex.disposables.CompositeDisposable

class BookAddressViewModel(private val bookAddressRepository: BookAddressRepository) : ViewModel() {
    val bookAddressListSuccess = MutableLiveData<List<BookAddressEntity>>()
    val addBookAddressSuccessObservable = MutableLiveData<Boolean>()
    val editBookAddressSuccess = MutableLiveData<BookAddressEntity>()
    val updateBookSuccessObservable = MutableLiveData<Boolean>()
    val bookAddressListError = MutableLiveData<String>()

    private val compositeDisposable = CompositeDisposable()

    fun getListBookAddress() {
        compositeDisposable.add(
            bookAddressRepository.getListBookAddress().subscribe({ bookAddressList ->
                bookAddressListSuccess.value = bookAddressList
            }, { error -> bookAddressListError.value = error.message })
        )
    }

    fun getBookAddress(idAddress: Int) {
        compositeDisposable.add(
            bookAddressRepository.getBookAddress(idAddress).subscribe({ editBook ->
                editBookAddressSuccess.value = editBook
            }, { error -> bookAddressListError.value = error.message })
        )
    }

    fun addBookAddress(note: BookAddressEntity) {
        compositeDisposable.add(
            bookAddressRepository.addBookAddress(note).subscribe(
                {
                    addBookAddressSuccessObservable.value = true
                }, { addBookAddressSuccessObservable.value = false }
            )
        )
    }

    fun updateBook(book: BookAddressEntity) {
        compositeDisposable.add(
            bookAddressRepository.updateBookAddress(book)
                .subscribe({ updateBookSuccessObservable.value = true },
                    { updateBookSuccessObservable.value = false })
        )
    }

    fun searchBookAddress(search: String) {
        compositeDisposable.add(
            bookAddressRepository.searchBookAddress(search).subscribe({ searchBook ->
                bookAddressListSuccess.value = searchBook }, { error -> bookAddressListError.value = error.message })
        )
    }
}