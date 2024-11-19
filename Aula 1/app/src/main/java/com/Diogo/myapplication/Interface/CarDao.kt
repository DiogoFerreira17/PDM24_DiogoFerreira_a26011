package com.Diogo.myapplication.Interface

import androidx.room.Insert
import androidx.room.Query
import com.Diogo.myapplication.Car
import java.util.concurrent.Flow

interface ExampleDao {
    @Insert
    suspend fun insert(car: Car)

    @Query("SELECT * FROM Cars WHERE id = :id")
    fun getById(id:Int):Flow<Car>

}