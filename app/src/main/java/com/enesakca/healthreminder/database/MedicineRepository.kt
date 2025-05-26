package com.enesakca.healthreminder.database

import androidx.lifecycle.LiveData
import java.time.LocalDate

//veri işlemlerini merkezi bir noktada toplamak icin

class MedicineRepository(private val medicineDao: MedicineDao,private val userDao : UserDao,private val medicineRecordDao: MedicineRecordDao) {

    suspend fun insertUser(user: User) = userDao.insertUser(user)
    fun getUser(userId: Int) = userDao.getUserById(userId)


    //son eklemelerden
    suspend fun getMedicinesByUserSuspend(userId: Int): List<Medicine> {
        return medicineDao.getMedicinesByUserSuspend(userId)
    }

    suspend fun getRecord(medicineId: Int, date: String): MedicineRecord? {
        return medicineRecordDao.getRecord(medicineId, date)
    }


    suspend fun getTodayMedicines(userId: Int): List<MedicineWithStatus> {
        val today = LocalDate.now().toString()
        val medicines = getMedicinesByUserSuspend(userId)
        return medicines.map { medicine ->
            val record = medicineRecordDao.getRecord(medicine.medicineID, today)
            MedicineWithStatus(medicine, record?.isTaken ?: false)
        }
    }




    suspend fun updateRecordStatus(medicineId: Int, isTaken: Boolean, date: String) {
        var record = medicineRecordDao.getRecord(medicineId, date)
        if (record == null) {
            record = MedicineRecord(medicineID = medicineId, date = date, isTaken = isTaken)
            medicineRecordDao.insertRecord(record)
        } else {
            val updated = record.copy(isTaken = isTaken)
            medicineRecordDao.updateMedicineRecord(updated)
        }
    }

    suspend fun decreaseMedicineStock(medicineId: Int) {
        val medicine = medicineDao.getMedicineById(medicineId)
        if (medicine != null && medicine.stock > 0) {
            val updated = medicine.copy(stock = medicine.stock - 1)
            medicineDao.updateMedicine(updated)
        }
    }

    suspend fun insertMedicine(medicine: Medicine) = medicineDao.insertMedicine(medicine)

    fun getMedicinesByUser(userId: Int): LiveData<List<Medicine>> {
        return medicineDao.getMedicinesByUser(userId)
    }

    fun getAllMedicines(): LiveData<List<Medicine>> = medicineDao.getAllMedicines()






}