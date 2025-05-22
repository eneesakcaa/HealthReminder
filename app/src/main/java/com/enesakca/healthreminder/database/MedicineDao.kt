package com.enesakca.healthreminder.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MedicineDao {
    @Insert
    suspend fun insertMedicine(medicine: Medicine)
    @Update
    suspend fun updateMedicine(medicine: Medicine)
    @Delete
    suspend fun deleteMedicine(medicine: Medicine)

    @Query("SELECT * FROM medicines")
    fun getAllMedicines(): LiveData<List<Medicine>>

    @Query("SELECT * FROM medicines WHERE stock <  alertStock")
    fun getLowStockMedicines(): LiveData<List<Medicine>>
}