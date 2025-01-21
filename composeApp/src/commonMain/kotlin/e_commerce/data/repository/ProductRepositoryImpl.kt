package e_commerce.data.repository

import androidx.sqlite.SQLiteException
import core.domain.DataError
import core.domain.EmptyResult
import core.domain.Result
import core.domain.map
import e_commerce.data.database.FavoriteProductDao
import e_commerce.data.mappers.toEntity
import e_commerce.data.mappers.toProduct
import e_commerce.data.network.RemoteProductDataSource
import e_commerce.domain.models.Product
import e_commerce.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Presentation -> Domain <- Data
// Data can access domain, presentation can access domain, but domain only access domain
class ProductRepositoryImpl(
    private val remoteProductDataSource: RemoteProductDataSource,
    private val favoriteProductDao: FavoriteProductDao,
) : ProductRepository {

    override suspend fun getProducts(): Result<List<Product>, DataError.Remote> {
        return remoteProductDataSource.getProducts().map { dto ->
            dto.products.map {
                it.toProduct()
            }
        }
    }

    override suspend fun getProductById(productId: String): Result<Product, DataError.Remote> {
        return remoteProductDataSource.getProductById(productId).map { dto ->
            dto.toProduct()
        }
    }

    override fun getFavoriteProducts(): Flow<List<Product>> {
        return favoriteProductDao
            .getFavoriteProducts()
            .map { productEntities ->
                productEntities.map {
                    it.toProduct()
                }
            }
    }

    override fun isProductFavorite(productId: String): Flow<Boolean> {
        return favoriteProductDao
            .getProductExistsById(productId)
    }

    override suspend fun markAsFavorites(product: Product): EmptyResult<DataError.Local> {
        return try {
            favoriteProductDao.upsert(product.toEntity())
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFromFavorites(productId: String) {
        favoriteProductDao.deleteFavoriteProductById(productId)
    }

}
