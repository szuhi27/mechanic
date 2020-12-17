package com.example.mechanic.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName= "cars")
data class Cars (

    @PrimaryKey(autoGenerate = true)
    val carId: Long = 0L,

    @ColumnInfo(name = "brand")
    var brand: String = "Unknown",

    @ColumnInfo(name = "model")
    var model: String = "Unknown",

    @ColumnInfo(name = "creationYear")
    var creationYear: Int = 0,

    @ColumnInfo(name = "problem")
    var problem: String = "Unknown")

