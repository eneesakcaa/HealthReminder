package com.enesakca.healthreminder.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicine_records")
data class MedicineRecord(@PrimaryKey(autoGenerate = true) val recordID :Int = 0,
                     val medicineID: Int,
                     val date : String,
                     val isTaken: Boolean = false)