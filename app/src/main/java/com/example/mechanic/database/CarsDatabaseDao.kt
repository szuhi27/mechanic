package com.example.mechanic.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CarsDatabaseDao {

    @Insert
    suspend fun insert(cars: Cars)

    @Update
    suspend fun update(cars: Cars)

    @Query("SELECT * from cars WHERE carId = :key")
    suspend fun get(key: Long): Cars

    @Query("DELETE FROM cars")
    suspend fun clear()

    @Query("SELECT * FROM cars ORDER BY carId DESC")
    fun getAllCars(): LiveData<List<Cars>>

    @Query("SELECT * from cars WHERE carId = :key")
    fun getCarWithId(key: Long): LiveData<Cars>

    @Delete
    fun delete(cars: Cars)

    @Query("DELETE FROM cars WHERE carId = :key")
    fun deleteById(key: Long)

}