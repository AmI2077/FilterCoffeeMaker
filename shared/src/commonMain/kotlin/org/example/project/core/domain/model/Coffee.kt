package org.example.project.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Coffee(
    val id: String = "",
    val title: String,
    val imagePath: String? = null,
    val roasting: String,
    val qGrade: String? = null,
    val tasteDescription: String,
    val density: Float,
    val acidity: Float,
    val processingMethod: String,
    val userDescription: String? = null,
)

val mockCoffee = Coffee(
    id = "",
    title = "Эфиопия Иргачефф",
    imagePath = null, // Сюда можно подставить ByteArray или ByteArray.toImageBitmap() в Coil
    roasting = "Под фильтр",
    qGrade = "85",
    tasteDescription = "Жасмин, бергамот, спелый персик, лимонная цедра",
    density = 0.4f,  // Легкое, чайное тело, характерное для этого региона
    acidity = 0.8f,  // Яркая, сочная цитрусовая кислотность
    processingMethod = "Мытый",
    userDescription = "Невероятно легкая и чистая чашка. На первом плане звучат отчетливые цветочные ноты жасмина, а на остывании проступает приятная сладость персика. Идеально для утреннего V60."
)

val roastingTypes = listOf("Светлая", "Средняя", "Темная")
val processingMethods = listOf("Сухая", "Натуральная", "Мытая")