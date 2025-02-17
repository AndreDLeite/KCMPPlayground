package e_commerce.data.network


import core.data.safeCall
import core.domain.DataError
import core.domain.Result
import e_commerce.data.dto.ProductDto
import e_commerce.data.dto.ProductResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "http://192.168.0.6:8080"

class KtorRemoteProductDataSource(
    private val httpClient: HttpClient,
): RemoteProductDataSource {
    override suspend fun getProducts(): Result<ProductResponseDto, DataError.Remote> {
        return safeCall {
            httpClient.get(
                urlString = "$BASE_URL/json/products-list"
            )
        }
    }

    override suspend fun getProductById(productId: String): Result<ProductDto, DataError.Remote> {
        return safeCall {
            httpClient.get(
                urlString = "$BASE_URL/json/product-info"
            ) {
                parameter("productId", productId)
            }
        }
    }
}
