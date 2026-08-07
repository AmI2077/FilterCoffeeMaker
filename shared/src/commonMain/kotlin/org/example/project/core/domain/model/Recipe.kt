package org.example.project.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val id: Int,
    val title: String,
    val coffee: Coffee,
    val userRating: String? = null,
    val brewTime: Int,
    val coffeeAmount: Int,
    val waterAmount: Int,
    val waterTemperature: Int,
    val brewSteps: List<BrewStep>
)

val mockRecipe = Recipe(
    id = 1,
    title = "Яркий V60 для Кении",
    coffee = Coffee(
        id = "",
        title = "Кения Ньери от Tasty Coffee",
        imagePath = null,
        roasting = "Светлая",
        qGrade = "86.5",
        tasteDescription = "Черная смородина, красная палочка, шиповник",
        density = 3.5f,
        acidity = 4.5f,
        processingMethod = "Мытый",
        userDescription = "Очень сочная чашка с выразительной ягодной кислотностью. На остывании становится слаще."
    ),
    userRating = "5.0",
    brewTime = 180, // 3 минуты (180 секунд)
    coffeeAmount = 15,
    waterAmount = 250,
    waterTemperature = 93,
    brewSteps = listOf(
        BrewStep(
            startTime = 0,
            endTime = 30,
            amountWater = 50, // Влить 50 г на этом шаге (всего на весах: 50 г)
            textHint = "Первый этап — предсмачивание (блуминг). Из свежеобжаренного кофе активно выходит углекислый газ, заставляя его «цвести»!",
        ),
        BrewStep(
            startTime = 30,
            endTime = 75,
            amountWater = 100, // Влить еще 100 г на этом шаге (всего на весах: 150 г)
            textHint = "Кофе мытой обработки, как эта Кения, обычно обладает более чистым вкусом и искрящейся ягодной кислотностью.",
        ),
        BrewStep(
            startTime = 75,
            endTime = 120,
            amountWater = 100, // Влить финальные 100 г на этом шаге (всего на весах: 250 г)
            textHint = "Старайтесь вливать воду мягкими круговыми движениями от центра к краям, не размывая кофейный слой у бумажного фильтра.",
        ),
        BrewStep(
            startTime = 120,
            endTime = 180,
            amountWater = 0, // Больше воду не льем (всего на весах остается 250 г), кофе просто прокапывает
            textHint = "На этом этапе вся вода должна равномерно пройти сквозь кофе. Бумажный фильтр задержит кофейные масла, сделав тело напитка чистым.",
        )
    )
)