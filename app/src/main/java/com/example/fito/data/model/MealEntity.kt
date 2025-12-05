package com.example.fito.data.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class MealEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name="meal_name")
    val mealName: String,
    @ColumnInfo(name="meal_calo")
    val mealCalories: Int,
    @ColumnInfo(name="meal_date")
    val date: Long,
    @ColumnInfo(name="meal_type")
    val mealType: String,

)