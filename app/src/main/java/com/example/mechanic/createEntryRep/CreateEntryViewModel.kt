package com.example.mechanic.createEntryRep

import android.widget.EditText
import androidx.lifecycle.*
import com.example.mechanic.database.Cars
import com.example.mechanic.database.CarsDatabaseDao
import kotlinx.coroutines.launch

class CreateEntryViewModel (
    dataSource: CarsDatabaseDao): ViewModel()    {

    val database = dataSource


    private suspend fun insert(cars: Cars){
        database.insert(cars)
    }


    fun onSave(brandT: EditText, modelT: EditText, creationT: EditText, problemT: EditText){
        viewModelScope.launch {
            val newCar = Cars()
            newCar.brand = brandT.text.toString()
            newCar.model = modelT.text.toString()
            val yearString = creationT.text.toString()
            newCar.creationYear = Integer.parseInt(yearString)
            newCar.problem = problemT.text.toString()
            insert(newCar)
        }
    }
}
