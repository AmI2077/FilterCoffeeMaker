package org.example.project.core.data.local.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.example.project.core.data.local.db.dao.BrewStepDao
import org.example.project.core.data.local.db.dao.CoffeeDao
import org.example.project.core.data.local.db.dao.FavouritesRecipesDao
import org.example.project.core.data.local.db.dao.RecentRecipesDao
import org.example.project.core.data.local.db.entities.CoffeeEntity
import org.example.project.core.data.local.db.entities.FavouritesRecipesEntity
import org.example.project.core.data.local.db.entities.RecentRecipeEntity
import org.example.project.core.domain.api.CoroutineDispatchers

// TODO "прописать миграцию для бд, чтобы не ебаться с версиями"

@Database(
    version = 7,
    entities = [CoffeeEntity::class, RecentRecipeEntity::class, FavouritesRecipesEntity::class]
)
@TypeConverters(Converters::class)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCoffeeDao(): CoffeeDao
    abstract fun getRecipeDao(): RecentRecipesDao
    abstract fun getBrewStepDao(): BrewStepDao
    abstract fun getFavouritesDao(): FavouritesRecipesDao
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<AppDatabase>,
    coroutineContext: CoroutineDispatchers,
): AppDatabase {
    return builder
        .fallbackToDestructiveMigration(true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(coroutineContext.io())
        .build()
}

expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}