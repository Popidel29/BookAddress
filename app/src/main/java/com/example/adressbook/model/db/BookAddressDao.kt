package com.example.adressbook.model.db

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface BookAddressDao {
    @Query("Select * FROM book_table ORDER BY id DESC")
    fun getListBookAddress(): Observable<List<BookAddressEntity>>

    @Query("SELECT * FROM book_table WHERE id == :idAddress")
    fun getBookAddress(idAddress: Int): Observable<BookAddressEntity>

    @Query("select * from book_table where  name like :search || '%' or lastName like :search || '%'")
    fun searchBookAddress(search: String): Observable<List<BookAddressEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBookAddress(note: BookAddressEntity): Completable

    @Update
    fun updateBookAddress(note: BookAddressEntity?): Completable
}