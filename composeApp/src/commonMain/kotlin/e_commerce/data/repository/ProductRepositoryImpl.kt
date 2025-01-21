package e_commerce.data.repository

import core.domain.DataError
import core.domain.Result
import core.domain.map
import e_commerce.data.mappers.toProduct
import e_commerce.data.network.RemoteProductDataSource
import e_commerce.domain.models.Product
import e_commerce.domain.repository.ProductRepository

// Presentation -> Domain <- Data
// Data can access domain, presentation can access domain, but domain only access domain
class ProductRepositoryImpl(
    private val remoteProductDataSource: RemoteProductDataSource,
): ProductRepository {

    override suspend fun getProducts(): Result<List<Product>, DataError.Remote> {
        return remoteProductDataSource.getProducts().map { dto ->
            dto.products.map {
                it.toProduct()
            }
        }
    }

}