package com.example.mechanic.carDetailRep

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mechanic.database.CarsDatabaseDao
import java.lang.IllegalArgumentException

class CarDetailViewModelFactory(
    private val carKey: Long,
    private val dataSource: CarsDatabaseDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CarDetailViewModel::class.java)) {
            return CarDetailViewModel(carKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}