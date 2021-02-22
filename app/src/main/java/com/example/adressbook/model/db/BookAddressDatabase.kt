package com.example.adressbook.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BookAddressEntity::class], version = 1)
abstract class BookAddressDatabase : RoomDatabase() {

    abstract fun bookAddressDao(): BookAddressDao

    companion object {
        @Volatile
        private var INSTANCE: BookAddressDatabase? = null

        fun getInstance(context: Context): BookAddressDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BookAddressDatabase::class.java,
                        "BookAddressDatabase"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}