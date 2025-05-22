package com.enesakca.healthreminder.database

//veri işlemlerini merkezi bir noktada toplamak icin

class MedicineRepository(private val db : AppDatabase) {

    suspend fun insertUser(user: User) = db.userDao().insertUser(user)
    fun getCurrentUser()= db.userDao().getCurrentUser() // veritabanındaki mevcut kullanıcıyı döndürür

    suspend fun insertMedicine(medicine: Medicine) = db.medicineDao().insertMedicine(medicine)
    fun getAllMedicines() = db.medicineDao().getAllMedicines()
    fun getLowStockMedicines() = db.medicineDao().getLowStockMedicines()

    suspend fun insertMedicineRecord(record: MedicineRecord) = db.medicineRecordDao().insertMedicineRecord(record)
    fun getTodaysRecord(today : String) = db.medicineRecordDao().getTodayMedicines(today)
}