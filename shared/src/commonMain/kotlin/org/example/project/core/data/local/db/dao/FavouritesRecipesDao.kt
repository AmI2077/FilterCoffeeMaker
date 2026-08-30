package org.example.project.core.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.example.project.core.data.local.db.entities.FavouritesRecipesEntity

@Dao
interface FavouritesRecipesDao {

    @Query("SELECT * FROM FavouritesRecipes")
    fun getFavouritesRecipes(): Flow<FavouritesRecipesEntity>

    @Insert
    suspend fun insertRecipe(recipe: FavouritesRecipesEntity)
}