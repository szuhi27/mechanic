package com.example.mechanic.carsListRep

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.mechanic.database.Cars

@BindingAdapter("carBrand")
fun TextView.setCarBrand(item: Cars){
    text = item.brand
}

@BindingAdapter("carModel")
fun TextView.setCarModel(item: Cars){
    text = item.model
}

@BindingAdapter("carYear")
fun TextView.setCarYear(item: Cars){
    text = item.creationYear.toString()
}

@BindingAdapter("carProblem")
fun TextView.setCarProblem(item: Cars){
    text = item.problem
}