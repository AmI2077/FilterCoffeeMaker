package org.example.project.features.coffeeDetails.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.example.project.core.data.extensions.toEntity
import org.example.project.core.data.extensions.toModel
import org.example.project.core.data.local.db.dao.CoffeeDao
import org.example.project.core.domain.model.Coffee

class CoffeeDetailsRepositoryImpl(
    private val coffeeDao: CoffeeDao,
    private val dispatcher: CoroutineDispatcher
) : CoffeeDetailsRepository {
    override suspend fun getCoffeeDetailsFlow(coffeeId: String): Flow<Coffee> {
        return withContext(dispatcher) {
            coffeeDao.getFlowCoffeeDetails(coffeeId)
                .map { it.toModel() }
        }
    }

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