package org.example.project.core.data.local.db

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import org.example.project.core.domain.model.BrewStep

class Converters {

    @TypeConverter
    fun brewStepToJson(brewSteps: List<BrewStep>): String {
        return Json.encodeToString(brewSteps)
    }

    @TypeConverter
    fun jsonToBrewStep(json: String): List<BrewStep> {
        return Json.decodeFromString(json)
    }
}