package org.example.project.core.data.impl.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.project.core.data.extensions.toEntity
import org.example.project.core.data.extensions.toModel
import org.example.project.core.data.local.db.dao.CoffeeDao
import org.example.project.core.domain.api.repository.CoffeeRepository
import org.example.project.core.domain.model.Coffee

class CoffeeRepositoryImpl(
   private val coffeeDao: CoffeeDao
): CoffeeRepository {
    override suspend fun getCoffeeDetails(coffeeId: String): Coffee? {
        return coffeeDao.getCoffeeDetails(coffeeId)?.toModel()
    }

    override fun getCoffeeDetailsFlow(coffeeId: String): Flow<Coffee> =
        coffeeDao.getFlowCoffeeDetails(coffeeId)
            .map {
                it.toModel()
            }

    override fun getUserCoffeeList(): Flow<List<Coffee>> =
        coffeeDao.getCoffeeList()
            .map { list -> list.map { it.toModel() } }

    override suspend fun addCoffee(coffee: Coffee): Boolean {
        val rowId = coffeeDao.insertCoffee(coffee.toEntity())

        return rowId > 0
    }


    override suspend fun updateCoffee(updatedCoffee: Coffee): Boolean {
        val id = coffeeDao.updateCoffee(updatedCoffee.toEntity())

        return id > 0
    }

    override suspend fun deleteCoffee(coffee: Coffee): Boolean {
        val id = coffeeDao.deleteCoffee(coffee.toEntity())

        return id > 0
    }
}
