package org.example.project.core.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.example.project.core.data.local.db.entities.RecentRecipeEntity

@Dao
interface RecentRecipesDao {

    @Query("SELECT * FROM RecentRecipes")
    fun getRecentRecipes(): Flow<List<RecentRecipeEntity>>

    @Insert
    suspend fun insertRecipe(recentRecipeEntity: RecentRecipeEntity)
}