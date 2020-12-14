package com.example.mechanic.carsListRep

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mechanic.database.Cars
import com.example.mechanic.databinding.ListItemCarBinding

class CarsAdapter : ListAdapter<Cars, CarsAdapter.ViewHolder>(CarsDiffCallback()){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemCarBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Cars){
            binding.car = item
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemCarBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}

class CarsDiffCallback : DiffUtil.ItemCallback<Cars>(){

    override fun areItemsTheSame(oldItem: Cars, newItem: Cars): Boolean{
        return oldItem.carId == newItem.carId
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Cars, newItem: Cars): Boolean {
        return oldItem == newItem
    }

}