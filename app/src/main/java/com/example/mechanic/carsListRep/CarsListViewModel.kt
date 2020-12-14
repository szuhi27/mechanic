package com.example.mechanic.carsListRep

import android.app.Application
import androidx.lifecycle.*
import com.example.mechanic.database.CarsDatabaseDao
import com.example.mechanic.formatCars
import kotlinx.coroutines.launch

class CarsListViewModel (
        dataSource: CarsDatabaseDao,
        application: Application): ViewModel(){

    val database = dataSource

    val cars = database.getAllCars()

    val carsString = Transformations.map(cars){cars ->
        formatCars(cars, application.resources)
    }

    private var _showSnackbarEvent = MutableLiveData<Boolean?>()

    val showSnackBarEvent: LiveData<Boolean?>
        get() = _showSnackbarEvent

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = null
    }

    private suspend fun clear() {
        database.clear()
    }

    fun onClear(){
        viewModelScope.launch {
            clear()
            _showSnackbarEvent.value = true
        }
    }
}