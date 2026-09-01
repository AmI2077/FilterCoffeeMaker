package org.example.project.core.data.local.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import org.example.project.core.domain.model.BrewStep

@Entity(
    tableName = "RecentRecipes",
    foreignKeys = [
        ForeignKey(
            entity = CoffeeEntity::class,
            parentColumns = ["id"],
            childColumns = ["coffeeEntityId"],
            onDelete = ForeignKey.CASCADE
        ),
    ],
    indices = [Index(value = ["coffeeEntityId"])]
)
data class RecentRecipeEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val coffeeEntityId: String,
    val userRating: String? = null,
    val brewTime: Int,
    val coffeeAmount: Int,
    val waterAmount: Int,
    val waterTemperature: Int,
    val brewSteps: List<BrewStep>
)
