package e_commerce.domain.repository

import core.domain.DataError
import core.domain.EmptyResult
import core.domain.Result
import e_commerce.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(): Result<List<Product>, DataError.Remote>
    suspend fun getProductById(productId: String): Result<Product, DataError.Remote>

    fun getFavoriteProducts(): Flow<List<Product>>
    fun isProductFavorite(productId: String): Flow<Boolean>

    suspend fun markAsFavorites(product: Product): EmptyResult<DataError.Local>
    suspend fun deleteFromFavorites(productId: String)

}