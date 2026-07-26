package org.example.project.core.data.local.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.example.project.core.domain.model.BrewStep

@Entity(
    tableName = "RecentRecipes",
    foreignKeys = [
        ForeignKey(
            entity = CoffeeEntity::class,
            parentColumns = ["id"],
            childColumns = ["coffeeEntityId"]
        ),
    ]
)
data class RecentRecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val coffeeEntityId: Int,
    val userRating: String? = null,
    val brewTime: Int,
    val coffeeAmount: Int,
    val waterAmount: Int,
    val waterTemperature: Int,
    val brewSteps: List<BrewStep>
)
