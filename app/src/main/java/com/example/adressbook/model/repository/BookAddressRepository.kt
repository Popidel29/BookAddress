package com.example.adressbook.model.repository

import com.example.adressbook.model.db.BookAddressEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface BookAddressRepository {
    fun getListBookAddress(): Observable<List<BookAddressEntity>>

    fun getBookAddress(id_address: Int): Observable<BookAddressEntity>

    fun searchBookAddress(search:String) : Observable<List<BookAddressEntity>>

    fun addBookAddress(note: BookAddressEntity) : Completable

    fun updateBookAddress(note: BookAddressEntity?) : Completable
}