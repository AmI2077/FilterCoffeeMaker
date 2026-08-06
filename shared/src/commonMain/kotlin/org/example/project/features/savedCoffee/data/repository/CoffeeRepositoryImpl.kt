package org.example.project.features.savedCoffee.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import org.example.project.core.data.extensions.toModel
import org.example.project.core.data.local.db.dao.CoffeeDao
import org.example.project.core.domain.model.Coffee
import org.example.project.features.savedCoffee.domain.api.CoffeeRepository

class CoffeeRepositoryImpl(
    val coffeeDao: CoffeeDao
) : CoffeeRepository {
    override fun getCoffeeList(): Flow<List<Coffee>> {
        return coffeeDao.getCoffeeList()
            .map { list ->
                list.map {
                    it.toModel()
                }
            }.flowOn(Dispatchers.IO)
    }
}