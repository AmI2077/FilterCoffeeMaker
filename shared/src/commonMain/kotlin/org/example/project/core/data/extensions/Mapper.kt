package org.example.project.core.data.extensions

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import org.example.project.core.data.local.db.entities.CoffeeEntity
import org.example.project.core.data.local.db.entities.RecentRecipeEntity
import org.example.project.core.data.network.dto.AiRequestDto
import org.example.project.core.domain.model.Coffee
import org.example.project.core.domain.model.Recipe

fun AiRequestDto.toJson(): JsonElement {
    return Json.encodeToJsonElement(this)
}

fun Coffee.toEntity(): CoffeeEntity {
    return CoffeeEntity(
        id = id,
        title = title,
        imagePath = imagePath ?: "",
        roasting = roasting,
        qGrade = qGrade,
        tasteDescription = tasteDescription,
        density = density,
        acidity = acidity,
        processingMethod = processingMethod,
        userDescription = userDescription
    )
}

fun CoffeeEntity.toModel(): Coffee {
    return Coffee(
        id = id,
        title = title,
        imagePath = imagePath,
        roasting = roasting,
        qGrade = qGrade,
        tasteDescription = tasteDescription,
        density = density,
        acidity = acidity,
        processingMethod = processingMethod,
        userDescription = userDescription
    )
}

fun Recipe.toEntity(coffeeId: Int): RecentRecipeEntity {
    return RecentRecipeEntity(
        id = this.id,
        coffeeEntityId = coffeeId,
        title = this.title,
        userRating = this.userRating,
        brewTime = this.brewTime,
        coffeeAmount = this.coffeeAmount,
        waterAmount = this.waterAmount,
        waterTemperature = this.waterTemperature,
        brewSteps = this.brewSteps
    )
}

fun RecentRecipeEntity.toModel(coffee: Coffee): Recipe {
    return Recipe(
        id = this.id,
        coffee = coffee,
        title = this.title,
        userRating = this.userRating,
        brewTime = this.brewTime,
        coffeeAmount = this.coffeeAmount,
        waterAmount = this.waterAmount,
        waterTemperature = this.waterTemperature,
        brewSteps = this.brewSteps
    )
}