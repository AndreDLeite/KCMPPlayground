package e_commerce.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object ProductDatabaseConstructor: RoomDatabaseConstructor<FavoriteProductDatabase> {
    override fun initialize(): FavoriteProductDatabase
}
