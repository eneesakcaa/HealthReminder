package com.enesakca.healthreminder.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class, Medicine::class, MedicineRecord::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun medicineDao(): MedicineDao
    abstract fun medicineRecordDao(): MedicineRecordDao

    companion object {
        @Volatile // farklı threadler ile uyumlu olmasını sağlar
        private var INSTANCE: AppDatabase? = null // instance ise bu veritabanının singleton olmasını sağlar

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) { // eğer veritabanı null ise yeni bir veritabanı başlatır
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "medicine_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

