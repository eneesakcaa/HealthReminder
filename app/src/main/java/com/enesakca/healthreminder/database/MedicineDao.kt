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

    @Query("SELECT * FROM medicines WHERE userId = :userId")
    fun getMedicinesByUser(userId: Int): LiveData<List<Medicine>>


    @Query("SELECT * FROM medicines WHERE medicineID = :id")
    suspend fun getMedicineById(id: Int): Medicine?
    @Query("SELECT * FROM medicines WHERE userId = :userId")
    suspend fun getMedicinesByUserSuspend(userId: Int): List<Medicine>
    @Update
    suspend fun updateMedicine(medicine: Medicine)
    @Delete
    suspend fun deleteMedicine(medicine: Medicine)



    @Query("SELECT * FROM medicines")
    fun getAllMedicines(): LiveData<List<Medicine>>



    @Query("SELECT * FROM medicines WHERE userId = :userId AND stock <  alertStock")
    fun getLowStockMedicines(userId: Int): LiveData<List<Medicine>>
}
