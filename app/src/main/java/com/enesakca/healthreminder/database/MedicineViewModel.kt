package com.enesakca.healthreminder.database

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesakca.healthreminder.PreferencesHelper
import kotlinx.coroutines.launch

class MedicineViewModel(application: Application): AndroidViewModel(application) {


    private val preferences = PreferencesHelper(application)
    private var repository = MedicineRepository(AppDatabase.getDatabase(application))


    var rememberMeChecked by mutableStateOf(preferences.rememberMe)
        private set

    fun handleLoginOrRegister(user: User, rememberMe: Boolean) {
        viewModelScope.launch {
            // Kullanıcıyı Room'a kaydet
            repository.insertUser(user)

            // SharedPreferences'a durumu kaydet
            preferences.rememberMe = rememberMe
            preferences.userId = user.userID // Room'dan otomatik generate edilen ID
        }
    }


    // Otomatik giriş kontrolü
    fun isUserLoggedIn(): Boolean {
        return preferences.rememberMe && preferences.userId != -1
    }

    // Çıkış Yap
    fun logout() {
        preferences.rememberMe = false
        preferences.userId = -1
    }

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

/*
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

}*/