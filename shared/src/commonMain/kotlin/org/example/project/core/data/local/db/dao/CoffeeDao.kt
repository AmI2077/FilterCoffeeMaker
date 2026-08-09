package org.example.project.core.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.example.project.core.data.local.db.entities.CoffeeEntity

@Dao
interface CoffeeDao {

    @Query("SELECT * FROM Coffee")
    fun getCoffeeList(): Flow<List<CoffeeEntity>>

    @Query("SELECT * FROM Coffee WHERE id = :coffeeId")
    suspend fun getCoffeeDetails(coffeeId: String): CoffeeEntity?

    @Insert(onConflict = REPLACE)
    suspend fun insertCoffee(coffeeEntity: CoffeeEntity)

    @Delete
    suspend fun deleteCoffee(coffeeEntity: CoffeeEntity)

    @Update
    suspend fun updateCoffee(coffeeEntity: CoffeeEntity)
}