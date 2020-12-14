package com.example.mechanic.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cars::class], version = 1, exportSchema = false)
abstract class CarsDatabase : RoomDatabase() {

    abstract val carsDatabaseDao: CarsDatabaseDao

    companion object{

        @Volatile
        private var INSTANCE: CarsDatabase? = null

        fun getInstance(context: Context): CarsDatabase{

            synchronized(this){

                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CarsDatabase::class.java,
                        "car_history_database"
                    )
                            .fallbackToDestructiveMigration()
                            .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}