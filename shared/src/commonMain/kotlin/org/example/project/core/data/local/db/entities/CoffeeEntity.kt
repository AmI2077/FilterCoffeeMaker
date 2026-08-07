package org.example.project.core.data.local.db.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Coffee",
)
data class CoffeeEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val imagePath: String,
    val roasting: String,
    val qGrade: String? = null,
    val tasteDescription: String,
    val density: Float,
    val acidity: Float,
    val processingMethod: String,
    val userDescription: String? = null,
)