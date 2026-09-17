package org.example.project.features.addCoffee.domain.useCases

import org.example.project.core.domain.api.repository.AiClientRepository
import org.example.project.core.domain.model.Coffee
import org.example.project.core.domain.model.Result
import kotlin.io.encoding.Base64

class CoffeeFromImageUseCase(
    private val repository: AiClientRepository
) {
    suspend operator fun invoke(imageByteArray: ByteArray): Result<Coffee>  {
        val imageBase64 = Base64.encode(imageByteArray)

        return repository.getCoffeeFromImage(imageBase64)
    }
}