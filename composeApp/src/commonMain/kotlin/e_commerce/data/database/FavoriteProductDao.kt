package e_commerce.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteProductDao {

    @Upsert
    suspend fun upsert(product: ProductEntity)

    @Query("SELECT * FROM ProductEntity")
    fun getFavoriteProducts(): Flow<List<ProductEntity>>

    @Query("SELECT EXISTS (SELECT 1 FROM ProductEntity WHERE id = :productId)")
    fun getProductExistsById(productId: String): Flow<Boolean>

    @Query("SELECT * FROM ProductEntity WHERE id = :productId")
    suspend fun getFavoriteProductById(productId: String): ProductEntity?

    @Query("DELETE FROM ProductEntity WHERE id = :productId")
    suspend fun deleteFavoriteProductById(productId: String)

}