package com.example.mechanic.carDetailRep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mechanic.database.Cars
import com.example.mechanic.database.CarsDatabaseDao
import kotlinx.coroutines.launch

class CarDetailViewModel(
    private val carKey: Long = 0L,
    dataSource: CarsDatabaseDao): ViewModel() {

    val database = dataSource

    private val car: LiveData<Cars>

    fun getCar() = car

    init{
        car = database.getCarWithId(carKey)
    }

    private val _navigateToCarsList = MutableLiveData<Boolean?>()

    val navigateToCarsList: LiveData<Boolean?>
        get() = _navigateToCarsList


    fun doneNavigating(){
        _navigateToCarsList.value = null
    }

    fun onBack(){
        viewModelScope.launch {
            _navigateToCarsList.value = true
        }
    }

    fun onDelete(){
        viewModelScope.launch {
            //database.deleteById(carKey)
            _navigateToCarsList.value = true
        }
    }

}