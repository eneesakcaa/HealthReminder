package com.enesakca.healthreminder.database

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesakca.healthreminder.PreferencesHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class MedicineViewModel(application: Application): AndroidViewModel(application) {




    private val _todayMedicines = MutableStateFlow<List<MedicineWithStatus>>(emptyList())
    val todayMedicines: StateFlow<List<MedicineWithStatus>> = _todayMedicines.asStateFlow()
    private val preferences = PreferencesHelper(application)
    private val repository: MedicineRepository
    var rememberMeChecked by mutableStateOf(preferences.rememberMe)
        private set





    val currentUser: LiveData<User>
    get() = repository.getUser(preferences.currentUserId)

    init {
        val db = AppDatabase.getDatabase(application)
        repository = MedicineRepository(
            userDao = db.userDao(),
            medicineDao = db.medicineDao(),
            medicineRecordDao = db.medicineRecordDao()

        )
        loadTodayMedicines()
    }


    suspend fun getTodayMedicines(userId: Int): List<MedicineWithStatus> {
        val today = LocalDate.now().toString()
        val medicines = repository.getMedicinesByUserSuspend(userId)
        return medicines.map { medicine ->
            val record = repository.getRecord(medicine.medicineID, today)
            MedicineWithStatus(medicine, record?.isTaken ?: false)
        }
    }
    val medicines: LiveData<List<Medicine>>
        get() = repository.getMedicinesByUser(preferences.currentUserId)
    /*
    val userMedicines: LiveData<List<Medicine>>
        get() = repository.getMedicinesByUser(preferences.currentUserId)
*/

    fun loadTodayMedicines() {
        viewModelScope.launch {
            _todayMedicines.value =
                repository.getTodayMedicines(preferences.currentUserId) // 👈 userId ekleyin
        }
    }

    fun updateStatus(medicineId: Int, isTaken: Boolean) {
        viewModelScope.launch {
            val today = LocalDate.now().toString()
            repository.updateRecordStatus(medicineId, isTaken, today)

            if (isTaken) {
                repository.decreaseMedicineStock(medicineId)
            }

            loadTodayMedicines()
        }
    }


    //Çıkış Yap
    fun logout() {
        preferences.rememberMe = false
        preferences.currentUserId = -1
    }

    fun loginUser(user: User, rememberMe: Boolean) {
        viewModelScope.launch {
            val userId = repository.insertUser(user).toInt()
            preferences.currentUserId = userId
            preferences.rememberMe = rememberMe
        }
    }


    // Otomatik giriş kontrolü
    fun isUserLoggedIn(): Boolean {
        return preferences.rememberMe && preferences.currentUserId != -1
    }


    fun addMedicine(medicine: Medicine) {
        viewModelScope.launch {
            repository.insertMedicine(
                medicine.copy(
                    userId = preferences.currentUserId
                )
            )
        }
    }



}

