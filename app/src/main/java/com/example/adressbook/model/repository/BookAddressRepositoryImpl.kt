package com.example.adressbook.model.repository

import android.app.Application
import com.example.adressbook.model.db.BookAddressDatabase
import com.example.adressbook.model.db.BookAddressEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BookAddressRepositoryImpl(application: Application) : BookAddressRepository {

    private val bookAddressDao =
        BookAddressDatabase.getInstance(application.applicationContext).bookAddressDao()

    override fun getListBookAddress(): Observable<List<BookAddressEntity>> {
        return bookAddressDao.getListBookAddress().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getBookAddress(id_address: Int): Observable<BookAddressEntity> {
        return bookAddressDao.getBookAddress(id_address).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun searchBookAddress(search: String): Observable<List<BookAddressEntity>> {
        return bookAddressDao.searchBookAddress(search).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun addBookAddress(note: BookAddressEntity): Completable {
        return bookAddressDao.addBookAddress(note).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun updateBookAddress(note: BookAddressEntity?): Completable {
        return bookAddressDao.updateBookAddress(note).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}