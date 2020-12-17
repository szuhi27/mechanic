package com.example.mechanic.carsListRep

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.mechanic.database.Cars

@BindingAdapter("carBrand")
fun TextView.setCarBrand(item: Cars?){
    item?.let { text = item.brand }

}

@BindingAdapter("carModel")
fun TextView.setCarModel(item: Cars?){
    item?.let { text = item.model }
}

@BindingAdapter("carYear")
fun TextView.setCarYear(item: Cars?){
    item?.let { text = item.creationYear.toString() }
}

@BindingAdapter("carProblem")
fun TextView.setCarProblem(item: Cars?){
    item?.let { text = item.problem }
}