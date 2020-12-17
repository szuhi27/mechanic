package com.example.mechanic.carsListRep

import android.annotation.SuppressLint
import android.view.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mechanic.R
import com.example.mechanic.database.Cars
import com.example.mechanic.databinding.ListItemCarBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.launch
import java.lang.ClassCastException

//class CarsAdapter : ListAdapter<Cars, CarsAdapter.ViewHolder>(CarsDiffCallback()){
class CarsAdapter(val clickListener: CarsListener): ListAdapter<DataItem, RecyclerView.ViewHolder>(CarsDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    private val ITEM_VIEW_TYPE_HEADER = 0
    private val ITEM_VIEW_TYPE_ITEM = 1

    fun addHeaderAndSubmitList(list: List<Cars>?) {
        adapterScope.launch {
            val items = when (list) {
                null ->  listOf(DataItem.Header)
                else -> listOf(DataItem.Header) + list.map { DataItem.CarsItem(it) }
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val item = getItem(position) as DataItem.CarsItem
                holder.bind(item.cars, clickListener)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.CarsItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    class ViewHolder private constructor(val binding: ListItemCarBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Cars, clickListener: CarsListener) {
            binding.car = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemCarBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class TextViewHolder(view: View): RecyclerView.ViewHolder(view){
        companion object{
            fun from(parent: ViewGroup): TextViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.header, parent, false)
                return TextViewHolder(view)
            }
        }
    }
}

class CarsDiffCallback : DiffUtil.ItemCallback<DataItem>(){
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}

class CarsListener(val clickListener: (carId: Long) -> Unit){
    fun onClick(car: Cars) = clickListener(car.carId)
}

sealed class DataItem{
    abstract val id: Long

    data class CarsItem(val cars: Cars): DataItem(){
        override val id = cars.carId
    }

    object Header : DataItem(){
        override val id = Long.MIN_VALUE
    }
}


/*
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

}*/