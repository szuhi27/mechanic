package com.example.mechanic.carsListRep

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mechanic.database.CarsDatabaseDao

class CarsListViewModelFactory(
        private val dataSource: CarsDatabaseDao,
        private val application: Application): ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarsListViewModel::class.java)){
            return CarsListViewModel(dataSource,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}