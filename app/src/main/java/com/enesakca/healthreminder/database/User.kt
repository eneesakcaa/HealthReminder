package com.enesakca.healthreminder.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "users")
data class User (@PrimaryKey(autoGenerate = true)val userID: Int = 0, val firstName : String,val lastName : String)