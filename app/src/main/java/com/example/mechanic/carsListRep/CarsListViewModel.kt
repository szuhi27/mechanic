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

    private val _navigateToMainMenu = MutableLiveData<Boolean?>()

    val navigateToMainMenu: LiveData<Boolean?>
        get() = _navigateToMainMenu

    fun donaNavigating(){
        _navigateToMainMenu.value = null
    }


    private val _navigateToCarDetail = MutableLiveData<Long>()
    val navigateToCarDetail: LiveData<Long>
        get() = _navigateToCarDetail

    fun onCarClicked(id: Long){
        _navigateToCarDetail.value = id
    }

    fun onCarDetailNavigated(){
        _navigateToCarDetail.value = null
    }

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

    fun onHome(){
        viewModelScope.launch {
            _navigateToMainMenu.value = true
        }
    }
}