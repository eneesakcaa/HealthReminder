package com.enesakca.healthreminder.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MedicineViewModel(application: Application): AndroidViewModel(application) {
    private val repository: MedicineRepository
    init {
        val db = AppDatabase.getDatabase(application)
        repository = MedicineRepository(db)
    }

    //kullanici islemleri

    fun insertUser(user: User)= viewModelScope.launch {
        repository.insertUser(user)
    }

    val currentUser: LiveData<User?> = repository.getCurrentUser()

    fun addMedicine(medicine: Medicine) = viewModelScope.launch {
        repository.insertMedicine(medicine)
    }

    val allMedicines: LiveData<List<Medicine>> = repository.getAllMedicines()

}