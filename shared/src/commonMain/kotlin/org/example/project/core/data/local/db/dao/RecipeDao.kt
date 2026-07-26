package org.example.project.core.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.example.project.core.data.local.db.entities.RecentRecipeEntity

@Dao
interface RecipeDao {

    @Query("SELECT * FROM RecentRecipes")
    fun getRecentRecipes(): Flow<List<RecentRecipeEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun insertRecipe(recentRecipeEntity: RecentRecipeEntity)
}