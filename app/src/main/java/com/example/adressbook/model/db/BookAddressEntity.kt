package com.example.adressbook.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
data class BookAddressEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name: String,
    val lastName: String,
    val email: String,
    val phoneNumber: Int,
    val address: String
)
