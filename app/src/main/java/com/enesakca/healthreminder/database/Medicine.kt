package com.enesakca.healthreminder.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicines")
data class Medicine(@PrimaryKey(autoGenerate = true) val medicineID : Int =0,
               val userId: Int,
               val name : String,
               val medicineDosage: Int,
               val startDate: String, // ilaç başlangıç tarihi
               val reminderTime: String, // hatırlatma zamanı
               //val repeatDays: String, //kaç gün arayla alınacak
               //val dailyDose: Int, // gunde kac kez alacak
               val stock :Int, //kac tablet ilac kaldı hesaplama
               val alertStock: Int )  //istenen stock sayısına gelince uyarı vermek icin
{
}