package com.enesakca.healthreminder.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicines")
data class Medicine(@PrimaryKey(autoGenerate = true) val medicineID : Int =0,
               val userId: Int,
               val name : String,
               val medicineDosage: Int,
               val startDate: String,
               val reminderTime: String,
               val stock :Int,
               val alertStock: Int )
{
}