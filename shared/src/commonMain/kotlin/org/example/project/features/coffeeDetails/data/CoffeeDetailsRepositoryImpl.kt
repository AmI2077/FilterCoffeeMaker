package org.example.project.features.coffeeDetails.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.example.project.core.data.extensions.toEntity
import org.example.project.core.data.extensions.toModel
import org.example.project.core.data.local.db.dao.CoffeeDao
import org.example.project.core.domain.model.Coffee

class CoffeeDetailsRepositoryImpl(
    private val coffeeDao: CoffeeDao,
    private val dispatcher: CoroutineDispatcher
) : CoffeeDetailsRepository {
    override suspend fun getCoffeeDetails(coffeeId: String): Coffee? =
        withContext(dispatcher) {
            coffeeDao.getCoffeeDetails(coffeeId)?.toModel()
        }

    override suspend fun editCoffee(coffee: Coffee) {
        withContext(dispatcher) {
            coffeeDao.updateCoffee(coffee.toEntity())
        }
    }
}