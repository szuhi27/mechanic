package com.example.mechanic.createEntryRep

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mechanic.database.CarsDatabaseDao
import java.lang.IllegalArgumentException

class CreateEntryViewModelFactory(
        private val dataSource: CarsDatabaseDao): ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T:ViewModel?> create(modelClass: Class<T>): T{
        if(modelClass.isAssignableFrom(CreateEntryViewModel::class.java)){
            return CreateEntryViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}