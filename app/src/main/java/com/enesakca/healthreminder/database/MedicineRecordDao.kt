package com.enesakca.healthreminder.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MedicineRecordDao {
    @Insert
    suspend fun insertRecord(record: MedicineRecord)


    @Update
    suspend fun updateMedicineRecord(record: MedicineRecord)

    @Query("SELECT * FROM medicine_records WHERE medicineID = :medicineId AND date = :date")
    suspend fun getRecord(medicineId: Int, date: String): MedicineRecord?


    @Query("SELECT * FROM medicine_records WHERE date = :today")
    fun getTodayMedicines(today : String): LiveData<List<MedicineRecord>>
}