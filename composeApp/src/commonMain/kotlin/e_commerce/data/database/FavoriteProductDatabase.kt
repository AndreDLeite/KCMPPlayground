package e_commerce.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ProductEntity::class],
    version = 1,
)
@ConstructedBy(ProductDatabaseConstructor::class)
abstract class FavoriteProductDatabase: RoomDatabase() {
    abstract val favoritesProductDao: FavoriteProductDao

    companion object {
        const val DATABASE_NAME = "products.db"
    }
}
