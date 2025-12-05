package com.example.fito.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fito.data.local.dao.ExerciseDao
import com.example.fito.data.local.dao.MealDao
import com.example.fito.data.local.dao.ScheduleDao
import com.example.fito.data.model.ExerciseEntity
import com.example.fito.data.model.MealEntity
import com.example.fito.data.model.ScheduleEntity

// 1. Khai báo Database
@Database(
    entities = [
        MealEntity::class,
        ExerciseEntity::class,
        ScheduleEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class FitoDatabase : RoomDatabase() {

    abstract fun mealDao(): MealDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun scheduleDao(): ScheduleDao

    companion object {

        @Volatile
        private var INSTANCE: FitoDatabase? = null

        fun getDatabase(context: Context): FitoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FitoDatabase::class.java,
                    "fito_database"
                )
                    .fallbackToDestructiveMigration(false)
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}