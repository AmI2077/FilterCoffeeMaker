package org.example.project.features.savedCoffee.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.example.project.core.data.extensions.toEntity
import org.example.project.core.data.extensions.toModel
import org.example.project.core.data.local.db.dao.CoffeeDao
import org.example.project.core.domain.api.CoroutineDispatchers
import org.example.project.core.domain.model.Coffee
import org.example.project.features.savedCoffee.domain.api.CoffeeRepository

class CoffeeRepositoryImpl(
    private val coffeeDao: CoffeeDao,
    private val dispatcher: CoroutineDispatchers
) : CoffeeRepository {
    override fun getCoffeeList(): Flow<List<Coffee>> {
        return coffeeDao.getCoffeeList()
            .map { list ->
                list.map {
                    it.toModel()
                }
            }.flowOn(Dispatchers.IO)
    }

    override suspend fun deleteCoffee(coffee: Coffee) {
        withContext(dispatcher.io()) {
            coffeeDao.deleteCoffee(coffee.toEntity())
        }
    }
}