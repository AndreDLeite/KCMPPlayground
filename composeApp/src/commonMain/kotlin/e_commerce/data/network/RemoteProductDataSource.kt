package e_commerce.data.network

import core.domain.DataError
import core.domain.Result
import e_commerce.data.dto.ProductDto
import e_commerce.data.dto.ProductResponseDto

interface RemoteProductDataSource {
    suspend fun getProducts(): Result<ProductResponseDto, DataError.Remote>
    suspend fun getProductById(productId: String): Result<ProductDto, DataError.Remote>
}