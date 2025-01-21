package e_commerce.domain.repository

import core.domain.DataError
import core.domain.Result
import e_commerce.domain.models.Product

interface ProductRepository {
    suspend fun getProducts(): Result<List<Product>, DataError.Remote>
    suspend fun getProductById(productId: String): Result<Product, DataError.Remote>
}