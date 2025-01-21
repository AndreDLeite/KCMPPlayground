package e_commerce.data.network


import core.data.safeCall
import core.domain.DataError
import core.domain.Result
import e_commerce.data.dto.ProductResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val BASE_URL = "http://192.168.0.23:8080"

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
}
